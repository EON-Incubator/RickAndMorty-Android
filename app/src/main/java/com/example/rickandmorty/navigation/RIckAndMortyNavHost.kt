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
            var characterInfo = characterState.character?.ID.toString()
            Log.d("rcheck", "RickAndMortyNavHost:  ${characterState.character?.ID}")
            Characters(
                characterState,

                onClick = { navController.navigate("character_detail/ $it") },
                onCharacterClick = { viewModel.selectCountry(it) }
            )
        }
        composable("character_detail/{charInfo}") {
            val viewModel = hiltViewModel<DetailedCharacterViewModel>()

            val characterState by viewModel.character.collectAsState()
            // viewModel.selectCountry(it.arguments?.getString("charInfo").toString())
            // Log.d("sec_check", "RickAndMortyNavHost:  ${it.arguments?.getString("charInfo")}")
            CharacterDetails(state = characterState)
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
            LocationScreen(locationsState)
//            LocationDetailScreen()
        }
        composable(LocationDetailsDestination.route) {
            LocationDetailScreen()
        }
        composable("search") {
            Search()
        }
    }
}