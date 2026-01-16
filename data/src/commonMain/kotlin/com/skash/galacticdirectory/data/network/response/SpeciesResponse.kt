package com.skash.galacticdirectory.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeciesResponse(
    @SerialName("average_height")
    val averageHeight: String,
    @SerialName("average_lifespan")
    val averageLifespan: String,
    @SerialName("classification")
    val classification: String,
    @SerialName("created")
    val created: String,
    @SerialName("designation")
    val designation: String,
    @SerialName("edited")
    val edited: String,
    @SerialName("eye_colors")
    val eyeColors: String,
    @SerialName("films")
    val films: List<String>,
    @SerialName("hair_colors")
    val hairColors: String,
    @SerialName("homeworld")
    val homeworld: String?,
    @SerialName("language")
    val language: String,
    @SerialName("name")
    val name: String,
    @SerialName("people")
    val people: List<String>,
    @SerialName("skin_colors")
    val skinColors: String,
    @SerialName("url")
    val url: String
)