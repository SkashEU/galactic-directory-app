package com.skash.galacticdirectory.di

import com.skash.forge.event.DefaultEventBus
import com.skash.forge.event.EventBus
import com.skash.forge.navigation.NavigationDispatcher
import com.skash.forge.navigation.nav2.DefaultNavigationDispatcher
import com.skash.forge.network.client.HttpClient
import com.skash.forge.network.ktor.KtorApiClient
import com.skash.galacticdirectory.data.database.AppDatabase
import com.skash.galacticdirectory.data.database.dao.CharacterDao
import com.skash.galacticdirectory.data.database.dao.RemoteKeysDao
import com.skash.galacticdirectory.data.database.getRoomDatabase
import com.skash.galacticdirectory.data.network.service.SwapiService
import com.skash.galacticdirectory.data.repository.CharacterRepositoryImpl
import com.skash.galacticdirectory.detail.presentation.DetailViewModel
import com.skash.galacticdirectory.domain.repository.CharacterRepository
import com.skash.galacticdirectory.domain.usecase.GetCharacterWithDetailsUseCase
import com.skash.galacticdirectory.domain.usecase.GetCharactersUseCase
import com.skash.galacticdirectory.domain.usecase.GetFavoritesUseCase
import com.skash.galacticdirectory.domain.usecase.ToggleFavoriteCharacterUseCase
import com.skash.galacticdirectory.event.UIEvent
import com.skash.galacticdirectory.feature.favorites.presentation.FavoritesViewModel
import com.skash.galacticdirectory.feature.home.presentation.HomeViewModel
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val appModule = module {
    single<NavigationDispatcher> { DefaultNavigationDispatcher() }
    single<EventBus<UIEvent>> { DefaultEventBus() }

    single<HttpClient> {
        KtorApiClient {
            json(Json {
                coerceInputValues = true
                ignoreUnknownKeys = true
            })
        }.client
    }
    single<SwapiService> { SwapiService(httpClient = get()) }

    single<AppDatabase> { AppDatabase.getRoomDatabase() }
    single<CharacterDao> { get<AppDatabase>().getPersonDao() }
    single<RemoteKeysDao> { get<AppDatabase>().getRemoteKeysDao() }

    single<CharacterRepository> { CharacterRepositoryImpl(swapiService = get(), database = get()) }

    factory { GetCharactersUseCase(characterRepository = get()) }
    factory { GetFavoritesUseCase(characterRepository = get()) }
    factory { GetCharacterWithDetailsUseCase(characterRepository = get()) }
    factory { ToggleFavoriteCharacterUseCase(characterRepository = get()) }

    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
    viewModelOf(::FavoritesViewModel)
}