package com.skash.galacticdirectory.detail.presentation

import com.skash.galacticdirectory.domain.model.CharacterWithDetails

sealed interface DetailState {

    sealed interface Intent {
        data object NavigateBack: Intent
    }

    data object Loading: DetailState

    data class Loaded(val details: CharacterWithDetails): DetailState {
        sealed interface Intent: DetailState.Intent {
            data object ToggleIsFavorite: Intent
        }
    }

    data object EntityNotAvailable: DetailState
}