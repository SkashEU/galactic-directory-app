package com.skash.galacticdirectory.feature.home.presentation

import com.skash.galacticdirectory.viewmodel.BaseViewModel

class HomeViewModel: BaseViewModel<HomeState, HomeState.Intent>(initialState = HomeState()) {

    override fun executeIntent(intent: HomeState.Intent) {
        TODO("Not yet implemented")
    }
}