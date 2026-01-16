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
import com.skash.galacticdirectory.designsystem.component.preview.ComponentPreviewTemplate
import com.skash.galacticdirectory.designsystem.component.template.Page
import com.skash.galacticdirectory.resources.Res
import com.skash.galacticdirectory.resources.favorites_empty_description
import com.skash.galacticdirectory.resources.favorites_empty_title
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun EmptyFavoritesPage() {
    Page(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(Res.string.favorites_empty_title),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(Res.string.favorites_empty_description),
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
