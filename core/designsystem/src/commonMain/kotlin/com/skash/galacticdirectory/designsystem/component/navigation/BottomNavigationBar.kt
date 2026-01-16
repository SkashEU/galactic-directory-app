package com.skash.galacticdirectory.designsystem.component.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.skash.galacticdirectory.designsystem.component.preview.ComponentPreviewTemplate

@Composable
fun <T> BottomNavigationBar(
    items: List<T>,
    selectedItem: T,
    icon: @Composable RowScope.(item: T) -> Unit,
    label: @Composable RowScope.(item: T) -> Unit,
    onItemSelected: (T) -> Unit,
) {

    NavigationBar(
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    icon(item)
                },
                label = {
                    label(item)
                },
                selected = item == selectedItem,
                onClick = {
                    onItemSelected(item)
                }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    data class BottomNavItem(
        val title: String,
        val icon: ImageVector,
    )

    val items = listOf(
        BottomNavItem(
            title = "Tab 1",
            icon = Icons.Default.Dashboard
        ),
        BottomNavItem(
            title = "Tab #2",
            icon = Icons.Default.Done
        )
    )

    ComponentPreviewTemplate {
        BottomNavigationBar(
            items = items,
            selectedItem = items.first(),
            icon = {
                Icon(imageVector = it.icon, contentDescription = null)
            },
            label = {
                Text(text = it.title)
            },
            onItemSelected = {}
        )
    }
}