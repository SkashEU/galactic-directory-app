package com.skash.galacticdirectory.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.skash.galacticdirectory.data.database.entity.CharacterSpeciesCrossRef
import com.skash.galacticdirectory.data.database.entity.CharacterWithDetailsRelation
import com.skash.galacticdirectory.data.database.entity.DetailedCharacterEntity
import com.skash.galacticdirectory.data.database.entity.CharacterEntity
import com.skash.galacticdirectory.data.database.entity.PlanetEntity
import com.skash.galacticdirectory.data.database.entity.SpeciesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(people: List<CharacterEntity>)

    @Query("SELECT * FROM characters WHERE name LIKE '%' || :query || '%' ORDER BY id ASC")
    fun pagingSource(query: String): PagingSource<Int, CharacterEntity>

    @Transaction
    @Query("SELECT * FROM detailed_characters WHERE id = :characterId")
    fun getCharacterWithDetailsAsFlow(characterId: Int): Flow<CharacterWithDetailsRelation?>

    @Transaction
    @Query("SELECT * FROM detailed_characters WHERE id = :characterId")
    fun getCharacterWithDetails(characterId: Int): CharacterWithDetailsRelation?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: DetailedCharacterEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlanet(planet: PlanetEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSpecies(species: List<SpeciesEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacterSpeciesCrossRefs(crossRefs: List<CharacterSpeciesCrossRef>)

    @Transaction
    suspend fun saveCharacterWithDetails(
        character: DetailedCharacterEntity,
        planet: PlanetEntity?,
        species: List<SpeciesEntity>
    ) {
        planet?.let { insertPlanet(it) }

        if (species.isNotEmpty()) {
            insertSpecies(species)
        }

        insertCharacter(character)

        val crossRefs = species.map {
            CharacterSpeciesCrossRef(characterId = character.id, speciesId = it.id)
        }
        insertCharacterSpeciesCrossRefs(crossRefs)
    }

    @Query("UPDATE detailed_characters SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)

    @Transaction
    @Query("SELECT * FROM detailed_characters WHERE isFavorite = 1")
    fun getFavoriteCharactersAsFlow(): Flow<List<CharacterWithDetailsRelation>>

    @Query("DELETE FROM characters")
    suspend fun clear()

}