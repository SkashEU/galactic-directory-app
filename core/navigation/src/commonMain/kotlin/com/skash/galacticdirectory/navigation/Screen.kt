package com.skash.galacticdirectory.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {

    @Serializable
    data object Graph : Screen

    @Serializable
    data class Details(val personId: Long) : Screen


}