package com.skash.galacticdirectory.feature.favorites.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.skash.galacticdirectory.designsystem.component.preview.ComponentPreviewTemplate
import com.skash.galacticdirectory.designsystem.component.template.PageWithPaddingSlot
import com.skash.galacticdirectory.designsystem.theme.Spacing
import com.skash.galacticdirectory.domain.model.CharacterWithDetails
import com.skash.galacticdirectory.domain.model.DetailedCharacter
import com.skash.galacticdirectory.domain.model.Planet
import com.skash.galacticdirectory.domain.model.Species
import com.skash.galacticdirectory.feature.favorites.ui.component.FavoriteCharacterCard

@Composable
internal fun FavoritesPage(
    favorites: List<CharacterWithDetails>,
    onCharacterClick: (Int) -> Unit,
    onToggleFavorite: (DetailedCharacter) -> Unit
) {

    PageWithPaddingSlot { contentPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {

            items(favorites, key = { it.detailedCharacter.id }) { character ->
                FavoriteCharacterCard(
                    character = character,
                    onClick = { onCharacterClick(character.detailedCharacter.id) },
                    onToggleFavorite = { onToggleFavorite(character.detailedCharacter) }
                )
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ComponentPreviewTemplate {
        FavoritesPage(
            favorites = listOf(
                CharacterWithDetails(
                    detailedCharacter = DetailedCharacter(
                        id = 1,
                        gender = "female",
                        name = "Leia Organa",
                        height = 150,
                        mass = 49,
                        hairColor = "brown",
                        skinColor = "light",
                        eyeColor = "brown",
                        birthYear = "19BBY",
                        isFavorite = true,
                        homeworldId = 1,
                        speciesIds = setOf(1)
                    ),
                    homeworld = Planet(
                        id = 1,
                        name = "Alderaan"
                    ),
                    species = listOf(
                        Species(
                            id = 1,
                            name = "Human"
                        )
                    )
                )
            ),
            onCharacterClick = {},
            onToggleFavorite = {}
        )
    }
}