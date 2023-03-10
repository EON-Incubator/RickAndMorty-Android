package com.example.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmorty.ui.screens.character.CharacterDestination
import com.example.rickandmorty.ui.screens.character.CharacterDetails
import com.example.rickandmorty.ui.screens.character.CharacterDetailsDestination
import com.example.rickandmorty.ui.screens.character.Characters
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
            Characters()
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