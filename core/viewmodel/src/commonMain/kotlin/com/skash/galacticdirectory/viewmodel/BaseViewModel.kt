package com.skash.galacticdirectory.viewmodel

import com.skash.forge.event.EventBus
import com.skash.forge.navigation.NavigationDispatcher
import com.skash.forge.viewmodel.StateViewModel
import com.skash.galacticdirectory.event.UIEvent
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

abstract class BaseViewModel<State : Any, Intent> : StateViewModel<State, Intent, UIEvent> {
    constructor(initialState: State, eventBus: EventBus<UIEvent>?) : super(
        initialState = initialState,
        eventBus = eventBus,
        navigationDispatcher = resolveNavigationDispatcher(),
    )

    constructor(initialState: State, useEventBus: Boolean = true) : this(
        initialState = initialState,
        eventBus = if (useEventBus) resolveEventBus() else null,
    )

    protected fun showSnackbar(message: String) {
        sendUIEvent(UIEvent.SnackBar(message))
    }

    private companion object Companion : KoinComponent {
        fun resolveEventBus(): EventBus<UIEvent> = get()

        fun resolveNavigationDispatcher(): NavigationDispatcher = get()
    }
}