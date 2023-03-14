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
            var characterInfo = characterState.character

            Characters(
                characterState,

                onClick = { navController.navigate(CharacterDetailsDestination.route) },
                onCharacterClick = { viewModel.selectCountry(it) }
            )
            Log.d("check", "RickAndMortyNavHost:  ${characterState.character?.ID}")
            Log.d("idcheck", "RickAndMortyNavHost:  ${characterInfo?.toString()}")
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
            Search()
        }
    }
}