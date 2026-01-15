package com.skash.galacticdirectory.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(
    tableName = "detailed_characters",
    foreignKeys = [
        ForeignKey(
            entity = PlanetEntity::class,
            parentColumns = ["id"],
            childColumns = ["homeworldId"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class DetailedCharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val gender: String,
    val height: Int,
    val mass: Int,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val isFavorite: Boolean,
    @ColumnInfo(index = true)
    val homeworldId: Int?
)