package com.skash.galacticdirectory.di

import com.skash.forge.navigation.NavigationDispatcher
import com.skash.forge.navigation.nav2.DefaultNavigationDispatcher
import org.koin.dsl.module

internal val appModule = module {
    single<NavigationDispatcher> { DefaultNavigationDispatcher() }
}