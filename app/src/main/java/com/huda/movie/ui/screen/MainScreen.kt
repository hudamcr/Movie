package com.huda.movie.ui.screen

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { AppBottomBar(navController = navController) },
    )
    { paddingValues ->
        BottomNavigationGraph(
            navController = navController,
            paddingModifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun AppBottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Tasks
    )
    BottomNavigation() {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    navController: NavHostController
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigationItem(
        label = {
            Text(text = screen.label)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.route + " icon"
            )
        },
        selected = screen.route == backStackEntry.value?.destination?.route,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

@Composable
fun BottomNavigationGraph(
    navController: NavHostController,
    paddingModifier: Modifier
) {
    val genreId = 28
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
                HomeScreen(paddingModifier, navController)
        }
        composable(route = BottomBarScreen.Tasks.route) {
            GenreScreen(paddingModifier, navController)
        }
        composable(
            route = "movieScreen/{genreId}",
            arguments = listOf(navArgument("genreId") { type = NavType.IntType })
        ) { backStackEntry ->
            val genreId = backStackEntry.arguments?.getInt("genreId")
            if (genreId != null) {
                MovieScreen(genreId, paddingModifier, navController)
            }
        }
        composable(
            "movieDetails/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            if (movieId != null) {
                MovieDetailsScreen(movieId, navController)
            }
        }
        composable(
            route = "movieDetails/{movieId}/review",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            if (movieId != null) {
                ReviewsScreen(movieId, paddingModifier)
            }
        }

    }
}

sealed class BottomBarScreen(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "home",
        label = "Home",
        icon = Icons.Rounded.Home
    )

    object Tasks : BottomBarScreen(
        route = "genre",
        label = "Genre",
        icon = Icons.Rounded.Info
    )
}