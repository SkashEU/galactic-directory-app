package com.skash.galacticdirectory.feature.favorites.presentation

import com.skash.galacticdirectory.domain.model.CharacterWithDetails
import com.skash.galacticdirectory.domain.model.DetailedCharacter

data class FavoritesState(
    val favorites: List<CharacterWithDetails> = emptyList()
) {

    sealed interface Intent {
        data class NavigateToDetails(val id: Int): Intent
        data class ToggleIsFavorite(val detailedCharacter: DetailedCharacter): Intent
    }
}

