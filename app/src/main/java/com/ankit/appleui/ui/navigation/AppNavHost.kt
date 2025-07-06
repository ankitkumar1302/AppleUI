package com.ankit.appleui.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import com.ankit.appleui.ui.screen.*

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object MLS : Screen("mls")
    object Downloads : Screen("downloads")
    object Search : Screen("search")
    object Detail : Screen("detail/{showId}") {
        fun createRoute(showId: String) = "detail/$showId"
    }
}

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        bottomBar = {
            if (backStackEntry?.destination?.route != Screen.Detail.route) {
                BottomNavBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(onShowClick = { id ->
                    navController.navigate(Screen.Detail.createRoute(id))
                })
            }
            composable(Screen.MLS.route) { MlsScreen() }
            composable(Screen.Downloads.route) { DownloadsScreen() }
            composable(Screen.Search.route) { SearchScreen() }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("showId") { type = NavType.StringType })
            ) { entry ->
                val showId = entry.arguments?.getString("showId") ?: return@composable
                ShowDetailScreen(showId = showId, navController = navController)
            }
        }
    }
}
