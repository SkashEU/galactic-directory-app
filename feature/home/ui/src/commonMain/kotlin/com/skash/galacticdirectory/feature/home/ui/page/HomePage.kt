package com.skash.galacticdirectory.feature.home.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.skash.galacticdirectory.designsystem.component.CharacterCard
import com.skash.galacticdirectory.designsystem.component.SearchField
import com.skash.galacticdirectory.designsystem.component.template.PageWithPaddingSlot
import com.skash.galacticdirectory.designsystem.theme.Spacing
import com.skash.galacticdirectory.domain.model.Character
import kotlinx.coroutines.flow.Flow

@Composable
internal fun HomePage(
    query: String,
    pagingData: Flow<PagingData<Character>>,
    onQueryChange: (String) -> Unit
) {

    val pagedItems = pagingData.collectAsLazyPagingItems()

    PageWithPaddingSlot { contentPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            item(key = "search_field") {
                SearchField(
                    query = query,
                    placeholder = "",
                    onQueryChange = onQueryChange
                )
            }

            items(pagedItems.itemCount) { index ->
                val character = pagedItems[index] ?: return@items

                CharacterCard(
                    name = character.name,
                    birthYear = character.birthYear,
                    onClick = {}
                )
            }
        }
    }
}