package com.example.rickandmorty.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.RickAndMortyNavHost
import com.example.rickandmorty.network.ConnectivityObserver
import com.example.rickandmorty.ui.AppViewModelProvider
import com.example.rickandmorty.ui.screens.character.CharacterDestination
import com.example.rickandmorty.ui.screens.commonUtils.BottomNavItem
import com.example.rickandmorty.ui.screens.commonUtils.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun RickAndMortyMainApp(
    navController: NavHostController = rememberNavController(),
    windowSize: WindowSizeClass,
    internetStatus: ConnectivityObserver.Status = ConnectivityObserver.Status.Lost,
) {
    val viewModel = hiltViewModel<AppViewModelProvider>()
    viewModel.setStatus(internetStatus = internetStatus)
    var invisible by remember { mutableStateOf(false) }
    var deviceType = ScreenType.PORTRAIT_PHONE

    Log.v("Rick And Morty Main", internetStatus.name.toString())
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
        backgroundColor = MaterialTheme.colors.background,

        bottomBar = {
            BottomNavigationBar(

                items = listOf(
                    BottomNavItem(
                        name = stringResource(id = R.string.characters_screen_title),
                        route = stringResource(R.string.characters_small_case),
                        icon = ImageVector.vectorResource(id = R.drawable.person_text_rectangle)
                    ),
                    BottomNavItem(
                        name = stringResource(id = R.string.locations_screen_title),
                        route = stringResource(R.string.locations_small_case),
                        icon = ImageVector.vectorResource(id = R.drawable.maplocation)
                    ),
                    BottomNavItem(
                        name = stringResource(id = R.string.episodes_screen_title),
                        route = stringResource(R.string.episodes_small_case),
                        icon = ImageVector.vectorResource(id = R.drawable.tvepisode)
                    ),
                    BottomNavItem(
                        name = stringResource(id = R.string.search_screen_title),
                        route = stringResource(id = R.string.search_small_case),
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
            deviceType = deviceType,
            internetStatus = internetStatus
        )
    }
}

enum class ScreenType {
    PORTRAIT_PHONE, LANDSCAPE_PHONE, PORTRAIT_TABLET, LANDSCAPE_TABLET,
}