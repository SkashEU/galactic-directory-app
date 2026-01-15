package com.skash.galacticdirectory.data.network.service

import com.skash.forge.network.client.HttpClient
import com.skash.forge.network.client.execute
import com.skash.forge.network.response.ApiResponse
import com.skash.galacticdirectory.data.network.endpoint.Endpoint
import com.skash.galacticdirectory.data.network.response.PeopleListResponse

class SwapiService(
    private val httpClient: HttpClient
) {
    suspend fun getPeople(page: Int): ApiResponse<PeopleListResponse> {
        return httpClient.execute<PeopleListResponse, PeopleListResponse>(
            mapper = {it},
            requestBuilder = {
                get(Endpoint.People)
                parameters {
                    put("page", page.toString())
                }
            }
        )
    }
}