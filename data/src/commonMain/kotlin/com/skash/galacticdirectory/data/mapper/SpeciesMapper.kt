package com.skash.galacticdirectory.data.mapper

import com.skash.galacticdirectory.data.network.response.SpeciesResponse
import com.skash.galacticdirectory.data.util.extractId
import com.skash.galacticdirectory.domain.model.Species

fun SpeciesResponse.toSpecies() = Species(
    id = url.extractId(),
    name = name,
)