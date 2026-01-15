package com.skash.galacticdirectory.di

import com.skash.forge.event.DefaultEventBus
import com.skash.forge.event.EventBus
import com.skash.forge.navigation.NavigationDispatcher
import com.skash.forge.navigation.nav2.DefaultNavigationDispatcher
import com.skash.forge.network.client.HttpClient
import com.skash.forge.network.ktor.KtorApiClient
import com.skash.galacticdirectory.data.database.AppDatabase
import com.skash.galacticdirectory.data.database.dao.PersonDao
import com.skash.galacticdirectory.data.database.dao.RemoteKeysDao
import com.skash.galacticdirectory.data.database.getRoomDatabase
import com.skash.galacticdirectory.data.network.service.SwapiService
import com.skash.galacticdirectory.data.repository.CharacterRepositoryImpl
import com.skash.galacticdirectory.domain.repository.CharacterRepository
import com.skash.galacticdirectory.domain.usecase.GetCharactersUseCase
import com.skash.galacticdirectory.event.UIEvent
import com.skash.galacticdirectory.feature.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<NavigationDispatcher> { DefaultNavigationDispatcher() }
    single<EventBus<UIEvent>> { DefaultEventBus() }

    single<HttpClient> { KtorApiClient().client }

    single<SwapiService> { SwapiService(get()) }

    single<AppDatabase> { AppDatabase.getRoomDatabase() }
    single<PersonDao> { get<AppDatabase>().getPersonDao() }
    single<RemoteKeysDao> { get<AppDatabase>().getRemoteKeysDao() }

    viewModelOf(::HomeViewModel)

    single<CharacterRepository> { CharacterRepositoryImpl(get(), get(), get()) }

    factory { GetCharactersUseCase(get()) }
}