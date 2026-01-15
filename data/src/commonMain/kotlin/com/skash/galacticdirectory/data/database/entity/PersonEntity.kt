package com.skash.galacticdirectory.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class PersonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val height: String
)