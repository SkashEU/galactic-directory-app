package com.skash.galacticdirectory.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.skash.forge.network.response.ApiResponse
import com.skash.galacticdirectory.data.database.AppDatabase
import com.skash.galacticdirectory.data.mapper.toDomain
import com.skash.galacticdirectory.data.mapper.toEntity
import com.skash.galacticdirectory.data.network.service.SwapiService
import com.skash.galacticdirectory.data.paging.PeopleRemoteMediator
import com.skash.galacticdirectory.data.util.collectAll
import com.skash.galacticdirectory.domain.model.Character
import com.skash.galacticdirectory.domain.model.CharacterWithDetails
import com.skash.galacticdirectory.domain.model.DetailedCharacter
import com.skash.galacticdirectory.domain.model.Planet
import com.skash.galacticdirectory.domain.model.Species
import com.skash.galacticdirectory.domain.repository.CharacterRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class CharacterRepositoryImpl(
    private val swapiService: SwapiService,
    private val database: AppDatabase
) : CharacterRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPaginatedCharacter(query: String): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = 10, initialLoadSize = 20, prefetchDistance = 10),
            remoteMediator = PeopleRemoteMediator(
                query = query,
                swapiService = swapiService,
                database = database
            ),
            pagingSourceFactory = { database.getPersonDao().pagingSource(query) }
        ).flow.map { pagingData ->
            pagingData.map { personEntity ->
                personEntity.toDomain()
            }
        }
    }

    override fun observeCharacterDetails(id: Int): Flow<CharacterWithDetails> {
        return database.getPersonDao().getCharacterWithDetailsAsFlow(id)
            .filterNotNull()
            .map { it.toDomain() }
            .onStart { refreshCharacterDetails(id) }
    }

    override fun observeFavorites(): Flow<List<CharacterWithDetails>> {
        return database.getPersonDao().getFavoriteCharactersAsFlow()
            .map { relations ->
                relations.map { it.toDomain() }
            }
    }

    override suspend fun setFavorite(id: Int, isFavorite: Boolean) {
        database.getPersonDao().updateFavoriteStatus(id, isFavorite)
    }

    private suspend fun refreshCharacterDetails(characterId: Int) {
        val characterResponse = swapiService.getCharacterById(characterId)

        // Dirty throw to simplify UI State handling
        if (characterResponse !is ApiResponse.Success && database.getPersonDao()
                .getCharacterWithDetails(characterId) == null
        ) {
            throw Exception("Refresh failed with empty cache...")
        }

        if (characterResponse is ApiResponse.Success) {
            val details = characterResponse.body

            val speciesCalls = details.speciesIds.map {
                suspend { swapiService.getSpeciesById(it) }
            }

            val planetCall = details.homeworldId?.let {
                suspend { swapiService.getPlanetById(it) }
            }

            coroutineScope {
                val speciesDeferred = async { collectAll(speciesCalls) }
                val planetDeferred = async { planetCall?.invoke() }

                val speciesResult = speciesDeferred.await()
                val planetResult = planetDeferred.await()

                if (speciesResult is ApiResponse.Success &&
                    (planetResult == null || planetResult is ApiResponse.Success)
                ) {

                    val speciesList = speciesResult.body
                    val planet = planetResult?.body

                    saveToDatabase(
                        characterId,
                        planet,
                        details,
                        speciesList
                    )
                }
            }
        }
    }

    private suspend fun saveToDatabase(
        charId: Int,
        planet: Planet?,
        details: DetailedCharacter,
        species: List<Species>
    ) {
        val isFavorite =
            database.getPersonDao().getCharacterWithDetails(charId)?.character?.isFavorite ?: false

        val charEntity = details.toEntity(isFavorite)
        val planetEntity = planet?.toEntity()
        val speciesEntities = species.map { it.toEntity() }

        database.getPersonDao().saveCharacterWithDetails(charEntity, planetEntity, speciesEntities)
    }
}
