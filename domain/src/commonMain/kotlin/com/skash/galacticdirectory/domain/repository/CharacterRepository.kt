package com.skash.galacticdirectory.domain.repository

import androidx.paging.PagingData
import com.skash.forge.network.response.ApiResponse
import com.skash.galacticdirectory.domain.model.Character
import com.skash.galacticdirectory.domain.model.CharacterWithDetails
import com.skash.galacticdirectory.domain.model.DetailedCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getPaginatedCharacter(query: String): Flow<PagingData<Character>>

    fun getCharacterDetailsAsFlow(id: Int): Flow<CharacterWithDetails>

    suspend fun setFavorite(id: Int, isFavorite: Boolean)
}