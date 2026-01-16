package com.skash.galacticdirectory.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.useWriterConnection
import com.skash.forge.network.response.ApiResponse
import com.skash.galacticdirectory.data.database.AppDatabase
import com.skash.galacticdirectory.data.database.entity.CharacterEntity
import com.skash.galacticdirectory.data.database.entity.RemoteKeys
import com.skash.galacticdirectory.data.network.service.SwapiService

@OptIn(ExperimentalPagingApi::class)
class PeopleRemoteMediator(
    private val query: String,
    private val swapiService: SwapiService,
    private val database: AppDatabase
) : RemoteMediator<Int, CharacterEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
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

        return when (val apiResponse = swapiService.getPeople(query = query, page = page)) {
            is ApiResponse.Error -> MediatorResult.Error(Exception(apiResponse.reason))
            is ApiResponse.Success -> {
                val people = apiResponse.body
                val endOfPaginationReached = people.next == null

                database.useWriterConnection {
                    if (loadType == LoadType.REFRESH) {
                        database.getRemoteKeysDao().clearRemoteKeys()
                        database.getPersonDao().clear()
                    }

                    val prevKey = if (page == 1) null else page - 1
                    val nextKey = if (endOfPaginationReached) null else page + 1

                    val keys = apiResponse.body.results.map { personDto ->
                        val id = extractIdFromUrl(personDto.url)
                        RemoteKeys(id = id, prevKey = prevKey, nextKey = nextKey)
                    }
                    val entities = people.results.map { personDto ->
                        CharacterEntity(
                            id = extractIdFromUrl(personDto.url),
                            name = personDto.name,
                            birthYear = personDto.birthYear
                        )
                    }

                    database.getRemoteKeysDao().insertAll(keys)
                    database.getPersonDao().insertAll(entities)
                }

                return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            }
        }
    }

    private fun extractIdFromUrl(url: String): Int {
        return url.trimEnd('/').substringAfterLast('/').toInt()
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CharacterEntity>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { person -> database.getRemoteKeysDao().remoteKeysId(person.id) }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CharacterEntity>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { person -> database.getRemoteKeysDao().remoteKeysId(person.id) }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, CharacterEntity>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.getRemoteKeysDao().remoteKeysId(id)
            }
        }
    }
}