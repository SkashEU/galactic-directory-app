package com.skash.galacticdirectory.data.repository

import com.skash.forge.network.response.ApiResponse
import com.skash.galacticdirectory.data.network.service.SwapiService
import com.skash.galacticdirectory.domain.model.Species
import com.skash.galacticdirectory.domain.repository.SpeciesRepository

class SpeciesRepositoryImpl(
    private val swapiService: SwapiService
): SpeciesRepository {
    override suspend fun getSpecies(speciesId: Int): ApiResponse<Species> {
        return swapiService.getSpeciesById(speciesId)
    }
}