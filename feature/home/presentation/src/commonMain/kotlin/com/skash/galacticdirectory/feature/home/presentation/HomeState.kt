package com.skash.galacticdirectory.feature.home.presentation

data class HomeState(
    val query: String = ""
) {

    sealed interface Intent{
        data class SetQuery(val query: String): Intent
    }
}