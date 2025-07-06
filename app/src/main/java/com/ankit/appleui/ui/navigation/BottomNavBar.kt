package com.ankit.appleui.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

sealed class BottomNavItem(val screen: Screen, val title: String, val icon: ImageVector) {
    object AppleTVPlus : BottomNavItem(Screen.Home, "Apple TV+", Icons.Filled.Home)
    object MLS : BottomNavItem(Screen.MLS, "MLS", Icons.Filled.Star)
    object Downloads : BottomNavItem(Screen.Downloads, "Downloads", Icons.Filled.PlayArrow)
    object Search : BottomNavItem(Screen.Search, "Search", Icons.Filled.Search)
}

@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.AppleTVPlus,
        BottomNavItem.MLS,
        BottomNavItem.Downloads,
        BottomNavItem.Search
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.Black,
        contentColor = Color.White
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.screen.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color(0xFF8A8A8A),
                    unselectedTextColor = Color(0xFF8A8A8A),
                    indicatorColor = Color.Black
                )
            )
        }
    }
}

@Composable
fun BottomNavBarPreview() {
    val navController = rememberNavController()
    BottomNavBar(navController)
}
