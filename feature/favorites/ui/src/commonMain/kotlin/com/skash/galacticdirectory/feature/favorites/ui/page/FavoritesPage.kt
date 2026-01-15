package com.skash.galacticdirectory.feature.favorites.ui.page

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.skash.galacticdirectory.designsystem.component.MetaTag
import com.skash.galacticdirectory.designsystem.component.template.PageWithPaddingSlot
import com.skash.galacticdirectory.designsystem.theme.Spacing
import com.skash.galacticdirectory.domain.model.CharacterWithDetails

@Composable
internal fun FavoritesPage(
    favorites: List<CharacterWithDetails>,
    onCharacterClick: (Int) -> Unit
) {

    PageWithPaddingSlot { contentPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {

            items(favorites) { character ->
                FavoriteCharacterCard(
                    character = character,
                    onClick = {onCharacterClick(character.detailedCharacter.id)},
                    onToggleFavorite = {  }
                )
            }
        }
    }
}

@Composable
private fun FavoriteCharacterCard(
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