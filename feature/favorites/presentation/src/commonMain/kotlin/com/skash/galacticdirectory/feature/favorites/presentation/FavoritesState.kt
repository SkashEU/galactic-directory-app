package com.skash.galacticdirectory.feature.favorites.presentation

import com.skash.galacticdirectory.domain.model.CharacterWithDetails

data class FavoritesState(
    val favorites: List<CharacterWithDetails> = emptyList()
) {

    sealed interface Intent {
        data class NavigateToDetails(val id: Int): Intent
    }
}

