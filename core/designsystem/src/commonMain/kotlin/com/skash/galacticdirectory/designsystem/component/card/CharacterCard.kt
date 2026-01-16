package com.skash.galacticdirectory.designsystem.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.skash.galacticdirectory.designsystem.component.preview.ComponentPreviewTemplate
import com.skash.galacticdirectory.designsystem.theme.Spacing

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    name: String,
    birthYear: String,
    onClick: () -> Unit
) {

    ElevatedCard(
        modifier = Modifier.fillMaxWidth().then(modifier),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(Spacing.Small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Birth Year: $birthYear", style = MaterialTheme.typography.bodySmall)
            }

            Icon(Icons.Default.ChevronRight, contentDescription = null)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ComponentPreviewTemplate {
        CharacterCard(
            name = "Luke Skywalker",
            birthYear = "19BBY",
            onClick = {}
        )
    }
}