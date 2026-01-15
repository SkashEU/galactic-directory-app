package com.skash.galacticdirectory.domain.repository

import androidx.paging.PagingData
import com.skash.galacticdirectory.domain.model.Character
import com.skash.galacticdirectory.domain.model.CharacterWithDetails
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getPaginatedCharacter(query: String): Flow<PagingData<Character>>

    fun observeCharacterDetails(id: Int): Flow<CharacterWithDetails>
    fun observeFavorites(): Flow<List<CharacterWithDetails>>

    suspend fun setFavorite(id: Int, isFavorite: Boolean)
}