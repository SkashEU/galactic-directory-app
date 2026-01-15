package com.skash.galacticdirectory.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlanetResponse(
    @SerialName("climate")
    val climate: String,
    @SerialName("created")
    val created: String,
    @SerialName("diameter")
    val diameter: String,
    @SerialName("edited")
    val edited: String,
    @SerialName("gravity")
    val gravity: String,
    @SerialName("name")
    val name: String,
    @SerialName("orbital_period")
    val orbitalPeriod: String,
    @SerialName("population")
    val population: String,
    @SerialName("residents")
    val residents: List<String>,
    @SerialName("rotation_period")
    val rotationPeriod: String,
    @SerialName("surface_water")
    val surfaceWater: String,
    @SerialName("terrain")
    val terrain: String,
    @SerialName("url")
    val url: String
)