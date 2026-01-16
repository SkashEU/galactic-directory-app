package com.skash.galacticdirectory.data.util

import com.skash.forge.network.response.ApiResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

internal suspend fun <T> collectAll(
    calls: List<suspend () -> ApiResponse<T>>
): ApiResponse<List<T>> = coroutineScope {
    val deferred = calls.map { async { it() } }
    val results = deferred.awaitAll()
    val firstError = results.filterIsInstance<ApiResponse.Error>().firstOrNull()

    if (firstError != null) {
        firstError
    } else {
        val bodies = results.map { (it as ApiResponse.Success).body }

        ApiResponse.Success(bodies)
    }
}