package com.skash.galacticdirectory.domain.model

data class DetailedCharacter(
    val id: Int,
    val gender: String,
    val name: String,
    val height: Int,
    val mass: Int,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val isFavorite: Boolean,
    val homeworldId: Int?,
    val speciesIds: Set<Int>
)