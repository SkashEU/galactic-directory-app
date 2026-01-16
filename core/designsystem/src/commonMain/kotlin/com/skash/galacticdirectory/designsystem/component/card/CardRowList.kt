package com.skash.galacticdirectory.designsystem.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skash.galacticdirectory.designsystem.component.preview.ComponentPreviewTemplate
import com.skash.galacticdirectory.designsystem.theme.Spacing

@Composable
fun <T> CardRowList(
    modifier: Modifier = Modifier,
    items: List<T>,
    content: @Composable ColumnScope.(T) -> Unit
) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth().then(modifier)
    ) {
        items.forEachIndexed { index, item ->
            content(item)
            if (index < items.lastIndex) {
                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = Spacing.Medium)
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ComponentPreviewTemplate {
        CardRowList(
            items = listOf("abc", "def"),
        ) {
            CharacterCard(
                name = it,
                birthYear = "19BBY",
                onClick = {}
            )
        }
    }
}