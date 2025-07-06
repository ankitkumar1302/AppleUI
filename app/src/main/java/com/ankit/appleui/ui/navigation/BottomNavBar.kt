package com.ankit.appleui.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.ankit.appleui.ui.theme.AppleUITheme

sealed class BottomNavItem(val title: String, val icon: ImageVector) {
    object AppleTVPlus : BottomNavItem("Apple TV+", Icons.Filled.Home)
    object MLS : BottomNavItem("MLS", Icons.Filled.Star)
    object Downloads : BottomNavItem("Downloads", Icons.Filled.PlayArrow)
    object Search : BottomNavItem("Search", Icons.Filled.Search)
}

@Composable
fun BottomNavBar() {
    val items = listOf(
        BottomNavItem.AppleTVPlus,
        BottomNavItem.MLS,
        BottomNavItem.Downloads,
        BottomNavItem.Search
    )
    
    var selectedItem by remember { mutableIntStateOf(0) }
    
    NavigationBar(
        containerColor = Color.Black,
        contentColor = Color.White,
        windowInsets = WindowInsets.navigationBars
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                icon = { 
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    ) 
                },
                label = { 
                    Text(text = item.title)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color(0xFF8A8A8A),
                    unselectedTextColor = Color(0xFF8A8A8A),
                    indicatorColor = Color.Black
                ),
                alwaysShowLabel = true
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun BottomNavBarPreview() {
    AppleUITheme {
        BottomNavBar()
    }
}
