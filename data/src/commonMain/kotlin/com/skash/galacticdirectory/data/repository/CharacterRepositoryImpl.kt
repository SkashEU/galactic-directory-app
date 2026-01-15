package com.skash.galacticdirectory.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.skash.galacticdirectory.data.database.dao.PersonDao
import com.skash.galacticdirectory.data.database.dao.RemoteKeysDao
import com.skash.galacticdirectory.data.mapper.toCharacter
import com.skash.galacticdirectory.data.network.service.SwapiService
import com.skash.galacticdirectory.data.paging.PeopleRemoteMediator
import com.skash.galacticdirectory.domain.model.Character
import com.skash.galacticdirectory.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepositoryImpl(
    private val swapiService: SwapiService,
    private val remoteKeysDao: RemoteKeysDao,
    private val personDao: PersonDao
) : CharacterRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPaginatedCharacter(query: String): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(20),
            remoteMediator = PeopleRemoteMediator(
                swapiService = swapiService,
                personDao = personDao,
                remoteKeysDao = remoteKeysDao
            ),
            pagingSourceFactory = { personDao.pagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { personEntity ->
                personEntity.toCharacter()
            }
        }
    }
}
