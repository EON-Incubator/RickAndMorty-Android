package com.example.rickandmorty.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.RickAndMortyNavHost
import com.example.rickandmorty.ui.screens.character.CharacterDestination
import com.example.rickandmorty.ui.screens.commonUtils.BottomNavItem
import com.example.rickandmorty.ui.screens.commonUtils.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickAndMortyMainApp(
    navController: NavHostController = rememberNavController(),
    windowSize: WindowSizeClass,
) {
    var invisible by remember { mutableStateOf(false) }
    var deviceType = ScreenType.PORTRAIT_PHONE
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            if (windowSize.heightSizeClass == WindowHeightSizeClass.Medium) {
                deviceType = ScreenType.PORTRAIT_PHONE
            }
        }
        WindowWidthSizeClass.Medium -> {
            if (windowSize.heightSizeClass == WindowHeightSizeClass.Expanded) {
                deviceType = ScreenType.PORTRAIT_TABLET
            } else if (windowSize.heightSizeClass == WindowHeightSizeClass.Compact) {
                deviceType = ScreenType.LANDSCAPE_PHONE
            }
        }
        else -> {
            deviceType = ScreenType.LANDSCAPE_TABLET
        }
    }

    Scaffold(
//        topBar = {
//            RickAndMortyTopAppBar(
//                title = "Rick And Morty",
//                canNavigateBack = false,
//                navigateUp = { navController.popBackStack() },
//                scrollBehavior = scrollBehavior,
//                invisible = invisible
//            )
//        },
        backgroundColor = MaterialTheme.colors.background,
//        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        bottomBar = {
            BottomNavigationBar(

                items = listOf(
                    BottomNavItem(
                        name = "Characters",
                        route = "characters",
                        icon = ImageVector.vectorResource(id = R.drawable.person_text_rectangle)
                    ),
                    BottomNavItem(
                        name = "Locations",
                        route = "locations",
                        icon = ImageVector.vectorResource(id = R.drawable.maplocation)
                    ),
                    BottomNavItem(
                        name = "Episodes",
                        route = "episodes",
                        icon = ImageVector.vectorResource(id = R.drawable.tvepisode)
                    ),
                    BottomNavItem(
                        name = "Search",
                        route = "search",
                        icon = Icons.Default.Search
                    )
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route) {
                        popUpTo(CharacterDestination.route) {
                            inclusive = false
                        }
                    }
                }
            )
        }
    ) {
        RickAndMortyNavHost(
            navController = navController,
            modifier = Modifier.padding(it),
            onDetailScreen = {
                invisible = it
            },
            deviceType = deviceType
        )
    }
}

enum class ScreenType {
    PORTRAIT_PHONE, LANDSCAPE_PHONE, PORTRAIT_TABLET, LANDSCAPE_TABLET,
}