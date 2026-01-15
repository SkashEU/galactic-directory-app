package com.skash.galacticdirectory.feature.home.ui.page

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.skash.galacticdirectory.designsystem.component.SearchField
import com.skash.galacticdirectory.designsystem.component.template.PageWithPaddingSlot

@Composable
internal fun HomePage(
    query: String,
    onQueryChange: (String) -> Unit
) {

    PageWithPaddingSlot {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item(key = "search_field") {
                SearchField(
                    query = query,
                    placeholder = "",
                    onQueryChange = onQueryChange
                )
            }
        }
    }
}