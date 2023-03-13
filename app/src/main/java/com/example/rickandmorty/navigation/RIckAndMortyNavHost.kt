package com.example.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmorty.ui.screens.character.*
import com.example.rickandmorty.ui.screens.episode.EpisodeDestination
import com.example.rickandmorty.ui.screens.episode.EpisodeDetails
import com.example.rickandmorty.ui.screens.episode.EpisodeDetailsDestination
import com.example.rickandmorty.ui.screens.episode.Episodes
import com.example.rickandmorty.ui.screens.location.*
import com.example.rickandmorty.ui.screens.search.Search

@Composable
fun RickAndMortyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = CharacterDestination.route) {
        composable(CharacterDestination.route) {
            val viewModel = hiltViewModel<CharacterViewModel>()
            val characterState by viewModel.characters.collectAsState()
            Characters(characterState)
        }
        composable(CharacterDetailsDestination.route) {
            CharacterDetails()
        }
        composable(EpisodeDestination.route) {
            Episodes()
        }
        composable(EpisodeDetailsDestination.route) {
            EpisodeDetails()
        }
        composable(LocationDestination.route) {
            val viewModel = hiltViewModel<LocationViewModel>()
            val locationsState by viewModel.location.collectAsState()
//            LocationScreen(locationsState)
            LocationDetailScreen()
        }
        composable(LocationDetailsDestination.route) {
            LocationDetailScreen()
        }
        composable("search") {
            Search()
        }
    }
}