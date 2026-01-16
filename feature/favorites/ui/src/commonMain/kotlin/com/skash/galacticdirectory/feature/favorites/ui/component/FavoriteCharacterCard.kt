package com.skash.galacticdirectory.feature.favorites.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.skash.galacticdirectory.designsystem.component.atom.MetaTag
import com.skash.galacticdirectory.designsystem.component.preview.ComponentPreviewTemplate
import com.skash.galacticdirectory.designsystem.theme.Spacing
import com.skash.galacticdirectory.domain.model.CharacterWithDetails
import com.skash.galacticdirectory.domain.model.DetailedCharacter
import com.skash.galacticdirectory.domain.model.Planet
import com.skash.galacticdirectory.domain.model.Species

@Composable
internal fun FavoriteCharacterCard(
    character: CharacterWithDetails,
    onClick: () -> Unit,
    onToggleFavorite: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(Spacing.Medium),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = character.detailedCharacter.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(Spacing.ExtraSmall))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    val speciesName = character.species.firstOrNull()?.name ?: "Unknown"
                    val planetName = character.homeworld?.name ?: "Unknown"

                    MetaTag(icon = Icons.Default.Fingerprint, text = speciesName)

                    Spacer(modifier = Modifier.width(Spacing.Small))

                    Text(
                        text = "•",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )

                    Spacer(modifier = Modifier.width(Spacing.Small))

                    MetaTag(icon = Icons.Default.Public, text = planetName)
                }
            }

            IconButton(onClick = onToggleFavorite) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Remove from favorites",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ComponentPreviewTemplate {
        FavoriteCharacterCard(
            character = CharacterWithDetails(
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
            ),
            onClick = {},
            onToggleFavorite = {}
        )
    }
}
