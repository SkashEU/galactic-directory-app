package com.skash.galacticdirectory.detail.presentation

import androidx.lifecycle.viewModelScope
import com.skash.forge.navigation.NavigationEvent
import com.skash.forge.outcome.onEachOutcome
import com.skash.galacticdirectory.domain.usecase.GetCharacterWithDetailsUseCase
import com.skash.galacticdirectory.domain.usecase.ToggleFavoriteCharacterUseCase
import com.skash.galacticdirectory.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailViewModel(
    characterId: Int,
    getCharacterWithDetailsUseCase: GetCharacterWithDetailsUseCase,
    private val toggleFavoriteCharacterUseCase: ToggleFavoriteCharacterUseCase,
) : BaseViewModel<DetailState, DetailState.Intent>(
    initialState = DetailState.Loading
) {

    private val details = getCharacterWithDetailsUseCase(characterId)
        .onEachOutcome(
            onFailure = { setState(DetailState.EntityNotAvailable) },
            onSuccess = { setState(DetailState.Loaded(it)) }
        )
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    override fun executeIntent(intent: DetailState.Intent) {
        when (intent) {
            DetailState.Intent.NavigateBack -> dispatchNavigationEvent(NavigationEvent.NavigateUp)
            is DetailState.Loaded.Intent.ToggleIsFavorite -> handleIntent<_, _>(
                intent = intent,
                handler = ::handleToggleIsFavorite
            )
        }
    }

    private fun handleToggleIsFavorite(
        state: DetailState.Loaded,
        intent: DetailState.Loaded.Intent.ToggleIsFavorite
    ) {
        viewModelScope.launch {
            toggleFavoriteCharacterUseCase(
                ToggleFavoriteCharacterUseCase.Input(
                    characterId = state.details.detailedCharacter.id,
                    isFavorite = state.details.detailedCharacter.isFavorite.not()
                )
            )
        }
    }
}