package com.skash.galacticdirectory.data.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CharacterWithDetailsRelation(
    @Embedded
    val character: DetailedCharacterEntity,

    @Relation(
        parentColumn = "homeworldId",
        entityColumn = "id"
    )
    val homeworld: PlanetEntity?,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = CharacterSpeciesCrossRef::class,
            parentColumn = "characterId",
            entityColumn = "speciesId"
        )
    )
    val species: List<SpeciesEntity>
)