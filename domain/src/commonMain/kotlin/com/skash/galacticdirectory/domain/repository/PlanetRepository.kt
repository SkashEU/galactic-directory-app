package com.skash.galacticdirectory.domain.repository

import com.skash.forge.network.response.ApiResponse
import com.skash.galacticdirectory.domain.model.Planet

interface PlanetRepository {
    suspend fun getPlanet(planetId: Int): ApiResponse<Planet>
}