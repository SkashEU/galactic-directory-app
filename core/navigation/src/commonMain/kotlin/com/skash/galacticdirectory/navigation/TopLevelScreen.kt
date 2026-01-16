package com.skash.galacticdirectory.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface TopLevelScreen : Screen {

    @Serializable
    data object Graph : TopLevelScreen

    @Serializable
    data object Home : TopLevelScreen

    @Serializable
    data object Favorites : TopLevelScreen
}