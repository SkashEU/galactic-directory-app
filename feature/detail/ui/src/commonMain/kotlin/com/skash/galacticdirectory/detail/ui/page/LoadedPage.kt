package com.skash.galacticdirectory.detail.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skash.galacticdirectory.designsystem.component.CardRowList
import com.skash.galacticdirectory.designsystem.component.ComponentPreviewTemplate
import com.skash.galacticdirectory.designsystem.component.DetailCard
import com.skash.galacticdirectory.designsystem.component.SectionHeader
import com.skash.galacticdirectory.designsystem.component.StatItem
import com.skash.galacticdirectory.designsystem.component.StatRow
import com.skash.galacticdirectory.designsystem.component.template.Page
import com.skash.galacticdirectory.designsystem.theme.Spacing
import com.skash.galacticdirectory.domain.model.CharacterWithDetails
import com.skash.galacticdirectory.domain.model.DetailedCharacter
import com.skash.galacticdirectory.domain.model.Planet

@Composable
internal fun LoadedPage(
    characterWithDetails: CharacterWithDetails,
    onToggleFavorite: () -> Unit
) {
    val stats = remember {
        listOf(
            StatItem(Icons.Default.Height, "Height", characterWithDetails.detailedCharacter.height.toString(), "cm"),
            StatItem(Icons.Default.MonitorWeight, "Mass", characterWithDetails.detailedCharacter.mass.toString(), "kg"),
            StatItem(Icons.Default.Palette, "Hair Color", characterWithDetails.detailedCharacter.hairColor),
            StatItem(Icons.Default.Face, "Skin Color", characterWithDetails.detailedCharacter.skinColor),
            StatItem(Icons.Default.Visibility, "Eye Color", characterWithDetails.detailedCharacter.eyeColor),
            StatItem(Icons.Default.Cake, "Birth Year", characterWithDetails.detailedCharacter.birthYear)
        )
    }

    Page(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = characterWithDetails.detailedCharacter.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = onToggleFavorite) {
                Icon(
                    imageVector = if (characterWithDetails.detailedCharacter.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = if (characterWithDetails.detailedCharacter.isFavorite) "Remove from favorites" else "Add to favorites",
                    tint = if (characterWithDetails.detailedCharacter.isFavorite) Color.Red else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        CardRowList(items = stats) {
            StatRow(it)
        }

        characterWithDetails.homeworld?.name?.let { name ->
            SectionHeader("Homeworld")
            DetailCard(
                icon = Icons.Default.Public,
                text = name
            )
        }

        if (characterWithDetails.species.isNotEmpty()) {
            SectionHeader("Species")
            characterWithDetails.species.forEach { species ->
                DetailCard(
                    icon = Icons.Default.Fingerprint,
                    text = species.name
                )
            }
        }
    }
}


@Composable
@Preview
private fun Preview() {
    ComponentPreviewTemplate {
        LoadedPage(
            characterWithDetails = CharacterWithDetails(
                detailedCharacter = DetailedCharacter(
                    id = 1,
                    gender = "Male",
                    name = "Luke Skywalker",
                    height = 172,
                    mass = 77,
                    hairColor = "Blond",
                    skinColor = "Fair",
                    eyeColor = "Blue",
                    birthYear = "19BBY",
                    isFavorite = true,
                    homeworldId = 1,
                    speciesIds = emptySet()
                ),
                homeworld = Planet(1, "Home"),
                species = emptyList()
            ),
            onToggleFavorite = {}
        )
    }
}