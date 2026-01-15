package com.skash.galacticdirectory.data.repository

import com.skash.forge.network.response.ApiResponse
import com.skash.galacticdirectory.data.network.service.SwapiService
import com.skash.galacticdirectory.domain.model.Planet
import com.skash.galacticdirectory.domain.repository.PlanetRepository

class PlanetRepositoryImpl(
    private val swapiService: SwapiService
): PlanetRepository {
    override suspend fun getPlanet(planetId: Int): ApiResponse<Planet> {
        return swapiService.getPlanetById(planetId)
    }
}