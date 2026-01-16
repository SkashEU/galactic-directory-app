package com.skash.galacticdirectory.data.network.service

import com.skash.forge.network.client.HttpClient
import com.skash.forge.network.client.execute
import com.skash.forge.network.response.ApiResponse
import com.skash.galacticdirectory.data.mapper.toDomain
import com.skash.galacticdirectory.data.network.endpoint.Endpoint
import com.skash.galacticdirectory.data.network.response.PeopleResponse
import com.skash.galacticdirectory.data.network.response.PeopleListResponse
import com.skash.galacticdirectory.data.network.response.PlanetResponse
import com.skash.galacticdirectory.data.network.response.SpeciesResponse
import com.skash.galacticdirectory.domain.model.DetailedCharacter
import com.skash.galacticdirectory.domain.model.Planet
import com.skash.galacticdirectory.domain.model.Species

class SwapiService(
    private val httpClient: HttpClient
) {
    suspend fun getPeople(query: String, page: Int): ApiResponse<PeopleListResponse> {
        return httpClient.execute<PeopleListResponse, PeopleListResponse>(
            mapper = { it },
            requestBuilder = {
                get(Endpoint.People)
                parameters {
                    put("page", page.toString())
                    if (query.isNotBlank()) {
                        put("search", query)
                    }
                }
            }
        )
    }

    suspend fun getCharacterById(id: Int): ApiResponse<DetailedCharacter> {
        return httpClient.execute<PeopleResponse, DetailedCharacter>(
            mapper = { it.toDomain() },
            requestBuilder = {
                get(Endpoint.People.Details(id))
            }
        )
    }

    suspend fun getPlanetById(id: Int): ApiResponse<Planet> {
        return httpClient.execute<PlanetResponse, Planet>(
            mapper = { it.toDomain() },
            requestBuilder = {
                get(Endpoint.Planet.Details(id))
            }
        )
    }

    suspend fun getSpeciesById(id: Int): ApiResponse<Species> {
        return httpClient.execute<SpeciesResponse, Species>(
            mapper = { it.toDomain() },
            requestBuilder = {
                get(Endpoint.Species.Details(id))
            }
        )
    }
}