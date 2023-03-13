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
            var idval = remember {
                mutableStateOf("")
            }
            Log.d(
                "idcheck",
                "RickAndMortyNavHost: $idval"
            )
            Characters(
                characterState,
                idval,
                onClick = { navController.navigate(CharacterDetailsDestination.route) }
            )
        }
        composable(CharacterDetailsDestination.route) {
            val viewModel = hiltViewModel<CharacterViewModel>()
            val characterState by viewModel.characters.collectAsState()
            CharacterDetails(viewModel = viewModel())
        }
        composable(EpisodeDestination.route) {
            Episodes()
        }
        composable(EpisodeDetailsDestination.route) {
            EpisodeDetails()
        }
        composable(LocationDestination.route) {
            LocationScreen()
        }
        composable(LocationDetailsDestination.route) {
            LocationDetails()
        }
        composable("search") {
            Search()
        }
    }
}