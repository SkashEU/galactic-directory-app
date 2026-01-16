package com.skash.galacticdirectory.detail.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skash.galacticdirectory.designsystem.component.card.CardRowList
import com.skash.galacticdirectory.designsystem.component.atom.SectionHeader
import com.skash.galacticdirectory.designsystem.component.atom.StatItem
import com.skash.galacticdirectory.designsystem.component.atom.StatRow
import com.skash.galacticdirectory.designsystem.component.card.DetailCard
import com.skash.galacticdirectory.designsystem.component.preview.ComponentPreviewTemplate
import com.skash.galacticdirectory.designsystem.component.template.Page
import com.skash.galacticdirectory.designsystem.theme.Spacing
import com.skash.galacticdirectory.domain.model.CharacterWithDetails
import com.skash.galacticdirectory.domain.model.DetailedCharacter
import com.skash.galacticdirectory.domain.model.Planet
import com.skash.galacticdirectory.resources.Res
import com.skash.galacticdirectory.resources.detail_favorite_add
import com.skash.galacticdirectory.resources.detail_favorite_remove
import com.skash.galacticdirectory.resources.detail_section_homeworld
import com.skash.galacticdirectory.resources.detail_section_species
import com.skash.galacticdirectory.resources.detail_stat_birth_year
import com.skash.galacticdirectory.resources.detail_stat_eye_color
import com.skash.galacticdirectory.resources.detail_stat_hair_color
import com.skash.galacticdirectory.resources.detail_stat_height
import com.skash.galacticdirectory.resources.detail_stat_height_unit
import com.skash.galacticdirectory.resources.detail_stat_mass
import com.skash.galacticdirectory.resources.detail_stat_mass_unit
import com.skash.galacticdirectory.resources.detail_stat_skin_color
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun LoadedPage(
    characterWithDetails: CharacterWithDetails,
    onToggleFavorite: () -> Unit
) {
    val stats = remember {
        listOf(
            StatItem(
                Icons.Default.Height,
                Res.string.detail_stat_height,
                characterWithDetails.detailedCharacter.height.toString(),
                Res.string.detail_stat_height_unit
            ),
            StatItem(
                Icons.Default.MonitorWeight,
                Res.string.detail_stat_mass,
                characterWithDetails.detailedCharacter.mass.toString(),
                Res.string.detail_stat_mass_unit
            ),
            StatItem(
                Icons.Default.Palette,
                Res.string.detail_stat_hair_color,
                characterWithDetails.detailedCharacter.hairColor
            ),
            StatItem(
                Icons.Default.Face,
                Res.string.detail_stat_skin_color,
                characterWithDetails.detailedCharacter.skinColor
            ),
            StatItem(
                Icons.Default.Visibility,
                Res.string.detail_stat_eye_color,
                characterWithDetails.detailedCharacter.eyeColor
            ),
            StatItem(
                Icons.Default.Cake,
                Res.string.detail_stat_birth_year,
                characterWithDetails.detailedCharacter.birthYear
            )
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
                    contentDescription = if (characterWithDetails.detailedCharacter.isFavorite) {
                        stringResource(Res.string.detail_favorite_remove)
                    } else {
                        stringResource(Res.string.detail_favorite_add)
                    },
                    tint = if (characterWithDetails.detailedCharacter.isFavorite) Color.Red else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        CardRowList(items = stats) {
            StatRow(
                StatItem(
                    icon = it.icon,
                    label = it.label,
                    value = it.value,
                    unit = it.unit
                )
            )
        }

        characterWithDetails.homeworld?.name?.let { name ->
            SectionHeader(stringResource(Res.string.detail_section_homeworld))
            DetailCard(
                icon = Icons.Default.Public,
                text = name
            )
        }

        if (characterWithDetails.species.isNotEmpty()) {
            SectionHeader(stringResource(Res.string.detail_section_species))
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
