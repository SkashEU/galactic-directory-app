package com.skash.galacticdirectory.data.database.entity

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "character_species_cross_ref",
    primaryKeys = ["characterId", "speciesId"],
    indices = [Index("speciesId")]
)
data class CharacterSpeciesCrossRef(
    val characterId: Int,
    val speciesId: Int
)