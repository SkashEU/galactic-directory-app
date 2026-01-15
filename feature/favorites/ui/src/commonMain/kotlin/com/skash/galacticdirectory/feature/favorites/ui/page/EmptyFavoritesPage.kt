package com.skash.galacticdirectory.feature.favorites.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skash.galacticdirectory.designsystem.component.ComponentPreviewTemplate
import com.skash.galacticdirectory.designsystem.component.template.Page

@Composable
internal fun EmptyFavoritesPage() {
    Page(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "These are not the favorites you're looking for.",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Your collection is as empty as the deserts of Tatooine.\nExplore the galaxy and tap the heart icon to add some!",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
@Preview
private fun Preview() {
    ComponentPreviewTemplate {
        EmptyFavoritesPage()
    }
}