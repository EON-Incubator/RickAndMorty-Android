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
            val viewModel = hiltViewModel<DetailedCharacterViewModel>()

            val characterState by viewModel.character.collectAsState()
            // viewModel.selectCountry(it.arguments?.getString("charInfo").toString())
            // Log.d("sec_check", "RickAndMortyNavHost:  ${it.arguments?.getString("charInfo")}")
            CharacterDetails(state = characterState)
        }
        composable(EpisodeDestination.route) {
            onDetailScreen(false)
            val viewModel = hiltViewModel<EpisodeViewModel>()
            val state by viewModel.state.collectAsState()
//            EpisodesScreen(state = state)
        }
        composable(EpisodeDetailsDestination.route) {
            onDetailScreen(true)
            EpisodeDetails()
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
            onDetailScreen(true)
//            val id = it.arguments?.getString("id")
            val viewModel = hiltViewModel<LocationDetailViewModel>()
            val locationsDetailState by viewModel.locationDetail.collectAsState()

            LocationDetailScreen(
                locationsDetailState,
                navigateUp = { navController.popBackStack() },
                onCharacterClick = {
                    Log.v("IDID", it)
                    navController.navigate(CharacterDetailsDestination.route + "?id=$it")
                }
            )
        }
        composable("search") {
            onDetailScreen(false)
            val viewModel = hiltViewModel<SearchViewModel>()
            val characterState by viewModel.characters.collectAsState()
            val locationState by viewModel.locations.collectAsState()

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
                }
            )
        }
    }
}