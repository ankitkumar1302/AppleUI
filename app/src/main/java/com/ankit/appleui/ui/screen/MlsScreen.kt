package com.ankit.appleui.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ankit.appleui.ui.component.SectionHeader
import com.ankit.appleui.ui.component.ShowCard
import com.ankit.appleui.ui.util.ImageResources

@Composable
fun MlsScreen() {
    val highlights = List(10) { index ->
        ImageResources.getShowItem(index)
    }

    val topPlays = List(6) { index ->
        ImageResources.getShowItem(index + 10)
    }

    LazyColumn {
        item {
            SectionHeader(title = "MLS Highlights", showChevron = false)
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(highlights) { show ->
                    ShowCard(show = show)
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            SectionHeader(title = "Top Plays", showChevron = false)
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(topPlays) { show ->
                    ShowCard(show = show)
                }
            }
        }

        item { Spacer(modifier = Modifier.height(64.dp)) }
    }
}
