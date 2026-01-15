package com.skash.galacticdirectory.feature.favorites.presentation

import androidx.lifecycle.viewModelScope
import com.skash.forge.navigation.NavigationEvent
import com.skash.forge.usecase.invoke
import com.skash.galacticdirectory.domain.usecase.GetFavoritesUseCase
import com.skash.galacticdirectory.navigation.Screen
import com.skash.galacticdirectory.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

class FavoritesViewModel(
    getFavoritesUseCase: GetFavoritesUseCase
) : BaseViewModel<FavoritesState, FavoritesState.Intent>(
    initialState = FavoritesState()
) {

    private val favorites = getFavoritesUseCase()
        .onEach {
            setState(FavoritesState(it))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    override fun executeIntent(intent: FavoritesState.Intent) {
        when(intent) {
            is FavoritesState.Intent.NavigateToDetails -> handleIntent<_, _>(
                intent = intent,
                handler = ::handleNavigateToDetails
            )
        }
    }

    private fun handleNavigateToDetails(
        state: FavoritesState,
        intent: FavoritesState.Intent.NavigateToDetails
    ) {
        val navigationEvent = NavigationEvent.NavigateTo(Screen.Details(intent.id))
        dispatchNavigationEvent(navigationEvent)
    }
}