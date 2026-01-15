package com.skash.galacticdirectory.domain.model

data class CharacterWithDetails(
    val detailedCharacter: DetailedCharacter,
    val homeworld: Planet?,
    val species: List<Species>
)