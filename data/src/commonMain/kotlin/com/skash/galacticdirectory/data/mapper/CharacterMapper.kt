package com.skash.galacticdirectory.data.mapper

import com.skash.galacticdirectory.data.database.entity.CharacterWithDetailsRelation
import com.skash.galacticdirectory.data.database.entity.DetailedCharacterEntity
import com.skash.galacticdirectory.data.database.entity.CharacterEntity
import com.skash.galacticdirectory.data.database.entity.PlanetEntity
import com.skash.galacticdirectory.data.database.entity.SpeciesEntity
import com.skash.galacticdirectory.data.network.response.PeopleResponse
import com.skash.galacticdirectory.data.util.extractId
import com.skash.galacticdirectory.domain.model.Character
import com.skash.galacticdirectory.domain.model.CharacterWithDetails
import com.skash.galacticdirectory.domain.model.DetailedCharacter
import com.skash.galacticdirectory.domain.model.Planet
import com.skash.galacticdirectory.domain.model.Species

fun CharacterEntity.toDomain(): Character = Character(
    id = id,
    name = name,
    birthYear = birthYear
)

fun PeopleResponse.toDomain() = DetailedCharacter(
    id = url.extractId(),
    gender = gender,
    name = name,
    height = height.toIntOrNull() ?: 0,
    mass = mass.toIntOrNull() ?: 0,
    hairColor = hairColor,
    skinColor = skinColor,
    eyeColor = eyeColor,
    birthYear = birthYear,
    isFavorite = false,
    homeworldId = homeworld?.extractId(),
    speciesIds = species.map { it.extractId() }.toSet()
)

fun DetailedCharacter.toEntity(isFavorite: Boolean): DetailedCharacterEntity {
    return DetailedCharacterEntity(
        id = id,
        name = name,
        gender = gender,
        height = height,
        mass = mass,
        hairColor = hairColor,
        skinColor = skinColor,
        eyeColor = eyeColor,
        birthYear = birthYear,
        isFavorite = isFavorite,
        homeworldId = homeworldId
    )
}

fun Planet.toEntity() = PlanetEntity(id = id, name = name)

fun Species.toEntity() = SpeciesEntity(id = id, name = name)

fun CharacterWithDetailsRelation.toDomain(): CharacterWithDetails {
    val domainDetailedCharacter = DetailedCharacter(
        id = character.id,
        name = character.name,
        gender = character.gender,
        height = character.height,
        mass = character.mass,
        hairColor = character.hairColor,
        skinColor = character.skinColor,
        eyeColor = character.eyeColor,
        birthYear = character.birthYear,
        isFavorite = character.isFavorite,
        homeworldId = character.homeworldId,
        speciesIds = species.map { it.id }.toSet()
    )

    val domainHomeworld = homeworld?.let { Planet(it.id, it.name) }

    val domainSpecies = species.map { Species(it.id, name = it.name) }

    return CharacterWithDetails(
        detailedCharacter = domainDetailedCharacter,
        homeworld = domainHomeworld,
        species = domainSpecies
    )
}