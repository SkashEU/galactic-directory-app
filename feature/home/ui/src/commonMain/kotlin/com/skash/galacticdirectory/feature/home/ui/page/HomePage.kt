package com.skash.galacticdirectory.feature.home.ui.page

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.skash.galacticdirectory.designsystem.component.SearchField
import com.skash.galacticdirectory.designsystem.component.template.PageWithPaddingSlot
import com.skash.galacticdirectory.domain.model.Character
import kotlinx.coroutines.flow.Flow

@Composable
internal fun HomePage(
    query: String,
    pagingData: Flow<PagingData<Character>>,
    onQueryChange: (String) -> Unit
) {

    val pagedItems = pagingData.collectAsLazyPagingItems()

    PageWithPaddingSlot {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item(key = "search_field") {
                SearchField(
                    query = query,
                    placeholder = "",
                    onQueryChange = onQueryChange
                )
            }

            items(pagedItems.itemCount) { index ->
                val character = pagedItems[index] ?: return@items

                Text(text = character.name)
            }
        }
    }
}