package com.skash.galacticdirectory.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "species")
data class SpeciesEntity(
    @PrimaryKey val id: Int,
    val name: String
)