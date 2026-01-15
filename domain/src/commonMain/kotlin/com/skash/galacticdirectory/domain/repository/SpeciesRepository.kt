package com.skash.galacticdirectory.domain.repository

import com.skash.forge.network.response.ApiResponse
import com.skash.galacticdirectory.domain.model.Species

interface SpeciesRepository {
    suspend fun getSpecies(speciesId: Int): ApiResponse<Species>
}