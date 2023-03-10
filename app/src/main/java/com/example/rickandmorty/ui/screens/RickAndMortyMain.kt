package com.example.rickandmorty.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.navigation.RickAndMortyNavHost
import com.example.rickandmorty.ui.screens.character.CharacterDetailsDestination
import com.example.rickandmorty.ui.screens.character.Characters


@Composable
fun RickAndMortyMainApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            RickAndMortyTopAppBar(
                title = "Rick And Morty",
                canNavigateBack = false,
                navigateUp = { navController.popBackStack() })
        },
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = "Characters",
                        route = "characters",
                        icon = Icons.Default.Person
                    ),
                    BottomNavItem(
                        name = "Episodes",
                        route = "episodes",
                        icon = Icons.Default.PlayArrow,
                    ),
                    BottomNavItem(
                        name = "Locations",
                        route = "locations",
                        icon = Icons.Default.LocationOn,
                    ),
                    BottomNavItem(
                        name = "Search",
                        route = "search",
                        icon = Icons.Default.Search,
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.currentBackStackEntry?.destination?.route ?: CharacterDetailsDestination.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    ) {
        RickAndMortyNavHost(navController = navController, modifier = Modifier.padding(it))
    }

}