package com.skash.galacticdirectory.data.mapper

import com.skash.galacticdirectory.data.network.response.PlanetResponse
import com.skash.galacticdirectory.data.util.extractId
import com.skash.galacticdirectory.domain.model.Planet

fun PlanetResponse.toDomain() = Planet(
    id = this.url.extractId(),
    name = this.name
)