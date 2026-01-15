package com.skash.galacticdirectory.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.skash.forge.network.response.ApiResponse
import com.skash.galacticdirectory.data.database.dao.PersonDao
import com.skash.galacticdirectory.data.database.dao.RemoteKeysDao
import com.skash.galacticdirectory.data.database.entity.PersonEntity
import com.skash.galacticdirectory.data.database.entity.RemoteKeys
import com.skash.galacticdirectory.data.network.service.SwapiService

@OptIn(ExperimentalPagingApi::class)
class PeopleRemoteMediator(
    private val swapiService: SwapiService,
    private val personDao: PersonDao,
    private val remoteKeysDao: RemoteKeysDao
) : RemoteMediator<Int, PersonEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PersonEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        val apiResponse = swapiService.getPeople(page)

        return when (apiResponse) {
            is ApiResponse.Error -> MediatorResult.Error(Exception(apiResponse.reason))
            is ApiResponse.Success -> {
                val people = apiResponse.body
                val endOfPaginationReached = people.next == null

                if (loadType == LoadType.REFRESH) {
                    remoteKeysDao.clearRemoteKeys()
                    personDao.clear()
                }

                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1

                val keys = apiResponse.body.results.map { personDto ->
                    val id = extractIdFromUrl(personDto.url)
                    RemoteKeys(id = id, prevKey = prevKey, nextKey = nextKey)
                }
                val entities = people.results.map { personDto ->
                    PersonEntity(
                        id = extractIdFromUrl(personDto.url),
                        name = personDto.name,
                        height = personDto.height
                    )
                }

                remoteKeysDao.insertAll(keys)
                personDao.insertAll(entities)

                return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            }
        }
    }

    private fun extractIdFromUrl(url: String): Int {
        return url.trimEnd('/').substringAfterLast('/').toInt()
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PersonEntity>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { person -> remoteKeysDao.remoteKeysId(person.id) }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PersonEntity>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { person -> remoteKeysDao.remoteKeysId(person.id) }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, PersonEntity>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.remoteKeysId(id)
            }
        }
    }
}