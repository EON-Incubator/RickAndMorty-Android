package com.example.rickandmorty.navigation

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmorty.ui.screens.character.*
import com.example.rickandmorty.ui.screens.episode.*
import com.example.rickandmorty.ui.screens.location.*
import com.example.rickandmorty.ui.screens.search.Search
import com.example.rickandmorty.ui.screens.search.SearchViewModel

@Composable
fun RickAndMortyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onDetailScreen: (Boolean) -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = CharacterDestination.route
    ) {
        composable(CharacterDestination.route) {
            onDetailScreen(false)
            val viewModel = hiltViewModel<CharacterViewModel>()
            val characterState by viewModel.characters.collectAsState()
            var characterInfo = characterState.character?.ID.toString()
            Log.d("rcheck", "RickAndMortyNavHost:  ${characterState.character?.ID}")
            Characters(
                characterState,

                onClick = {
                    Log.v("id", it.toString())
                    navController.navigate(CharacterDetailsDestination.route + "?id=$it")
                },
                onCharacterClick = { viewModel.selectCountry(it) }
            )
        }
        composable(CharacterDetailsDestination.route + "?id={id}") {
            onDetailScreen(true)
            val viewModel = hiltViewModel<DetailedCharacterViewModel>()

            val characterState by viewModel.character.collectAsState()
            CharacterDetails(
                state = characterState,
                navigateUp = { navController.popBackStack() }
            )
        }
        composable(EpisodeDestination.route) {
            onDetailScreen(false)
            val viewModel = hiltViewModel<EpisodeViewModel>()
            val state by viewModel.state.collectAsState()
            EpisodesScreen(
                state = state,
                onSelectEpisode = {
                    navController.navigate(EpisodeDetailsDestination.route + "?id=$it")
                }
            )
        }
        composable(EpisodeDetailsDestination.route + "?id={id}") {
            onDetailScreen(true)
            val viewModel = hiltViewModel<EpisodeDetailViewModel>()
            val state by viewModel.state.collectAsState()
            EpisodeDetails(state = state)
        }
        composable(LocationDestination.route) {
            onDetailScreen(false)
            // For Location Screen
            val viewModel = hiltViewModel<LocationViewModel>()
            val locationsState by viewModel.location.collectAsState()

            LocationScreen(
                locationsState,
                onClick = {
                    navController.navigate(LocationDetailsDestination.route + "?id=$it")
                }

            )

            // For Location Detail Screen
//            val viewModel = hiltViewModel<LocationDetailViewModel>()
//            val locationsDetailState by viewModel.locationDetail.collectAsState()
//            LocationDetailScreen(locationsDetailState)
        }
        composable(LocationDetailsDestination.route + "?id={id}") {
            val id = it.arguments?.getString("id")
            val viewModel = hiltViewModel<LocationDetailViewModel>()
            val locationsDetailState by viewModel.locationDetail.collectAsState()
            LocationDetailScreen(
                locationsDetailState
            )
        }
        composable("search") {
            val viewModel = hiltViewModel<SearchViewModel>()
            val characterState by viewModel.characters.collectAsState()
            val locationState by viewModel.locations.collectAsState()
            var showCharacters by remember {
                mutableStateOf(false)
            }
            var showLocations by remember {
                mutableStateOf(false)
            }

            Search(
                characterState = characterState,
                locationState = locationState,
                onValueChange = { viewModel.onSearch(it) },
                query = viewModel.query,
                onLocationClick = {
                    navController
                        .navigate(LocationDetailsDestination.route + "?id=$it")
                },
                onCharacterClick = {
                    navController
                        .navigate(CharacterDetailsDestination.route + "?id=$it")
                },
                onShowCharacters = { showCharacters = !showCharacters },
                onShowLocations = { showLocations = !showLocations },
                showCharacters = showCharacters,
                showLocations = showLocations
            )
        }
    }
}