package com.ankit.appleui.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ankit.appleui.ui.component.ShowCard
import com.ankit.appleui.ui.component.ShowItem
import com.ankit.appleui.ui.util.ImageResources

@Composable
fun SearchScreen() {
    data class SearchItem(val show: ShowItem, val category: String)

    var query by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }

    val categories = listOf("All", "Movies", "Series", "Sports")

    val items = remember {
        List(20) { index ->
            val category = when (index % 3) {
                0 -> "Movies"
                1 -> "Series"
                else -> "Sports"
            }
            SearchItem(ImageResources.getShowItem(index), category)
        }
    }

    val filtered = items.filter { item ->
        (selectedCategory == "All" || item.category == selectedCategory) &&
            item.show.title.contains(query, ignoreCase = true)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = query,
            onValueChange = { query = it },
            placeholder = { Text(text = "Search") },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF1C1C1E),
                unfocusedContainerColor = Color(0xFF1C1C1E),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedPlaceholderColor = Color(0xFFAAAAAA),
                unfocusedPlaceholderColor = Color(0xFFAAAAAA),
                cursorColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        TabRow(
            selectedTabIndex = categories.indexOf(selectedCategory),
            containerColor = Color.Black,
            contentColor = Color.White
        ) {
            categories.forEachIndexed { index, title ->
                Tab(
                    selected = selectedCategory == title,
                    onClick = { selectedCategory = title },
                    text = { Text(text = title) }
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(filtered) { item ->
                ShowCard(show = item.show)
            }
        }
    }
}
