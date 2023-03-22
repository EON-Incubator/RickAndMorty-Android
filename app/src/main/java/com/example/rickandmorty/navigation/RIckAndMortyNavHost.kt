package com.example.rickandmorty.navigation

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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
    val viewModel = hiltViewModel<SearchViewModel>()
    val searchResultState by viewModel.searchResult.collectAsState()
    var showCharacters by remember {
        mutableStateOf(true)
    }
    var showLocations by remember {
        mutableStateOf(true)
    }

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
            val listState = rememberLazyGridState()

            if (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ==
                listState.layoutInfo.totalItemsCount - 1
            ) {
                viewModel.updateList()
            }
            Characters(
                characterState,
                onClick = {
                    navController.navigate(CharacterDetailsDestination.route + "?id=$it")
                },
                onCharacterClick = { viewModel.selectCountry(it) },
                listState = listState
            )
        }
        composable(CharacterDetailsDestination.route + "?id={id}") {
            onDetailScreen(true)
            val viewModel = hiltViewModel<DetailedCharacterViewModel>()

            val characterState by viewModel.character.collectAsState()
            CharacterDetails(
                state = characterState,
                navigateUp = { navController.popBackStack() },
                onEpisodeClick = {
                    navController.navigate(EpisodeDetailsDestination.route + "?id=$it")
                },
                onOriginClick = {
                    navController
                        .navigate(LocationDetailsDestination.route + "?id=$it")
                },
                onLastSeenClick = {
                    navController
                        .navigate(LocationDetailsDestination.route + "?id=$it")
                }
            )
        }
        composable(EpisodeDestination.route) {
            onDetailScreen(false)
            val viewModel = hiltViewModel<EpisodeViewModel>()
            val state by viewModel.state.collectAsState()
            val listState = rememberLazyListState()

            if (
                listState.layoutInfo.visibleItemsInfo.lastOrNull()
                    ?.index == listState.layoutInfo.totalItemsCount - 1
            ) {
                viewModel.updateEpisodeList()
            }
            EpisodesScreen(
                state = state,
                onSelectEpisode = {
                    navController.navigate(EpisodeDetailsDestination.route + "?id=$it")
                },
                listState = listState
            )
        }
        composable(EpisodeDetailsDestination.route + "?id={id}") {
            onDetailScreen(true)
            val viewModel = hiltViewModel<EpisodeDetailViewModel>()
            val state by viewModel.state.collectAsState()
            EpisodeDetails(
                state = state,
                navigateUp = { navController.popBackStack() },
                onCharacterClick = {
                    navController.navigate(CharacterDetailsDestination.route + "?id=$it")
                }
            )
        }
        composable(LocationDestination.route) {
            onDetailScreen(false)
            val viewModel = hiltViewModel<LocationViewModel>()
            val locationsState by viewModel.location.collectAsState()
            val listState = rememberLazyListState()

            if (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ==
                listState.layoutInfo.totalItemsCount - 1
            ) {
                viewModel.updateList()
            }

            LocationScreen(
                locationsState,
                onClick = {
                    navController.navigate(LocationDetailsDestination.route + "?id=$it")
                },
                listState = listState
            )
        }
        composable(LocationDetailsDestination.route + "?id={id}") {
            onDetailScreen(true)
            val id = it.arguments?.getString("id")
            val viewModel = hiltViewModel<LocationDetailViewModel>()
            val locationsDetailState by viewModel.locationDetail.collectAsState()
            LocationDetailScreen(
                locationsDetailState,
                navigateUp = { navController.popBackStack() },
                onCharacterClick = {
                    navController.navigate(CharacterDetailsDestination.route + "?id=$it")
                }
            )
        }
        composable("search") {
            Search(
                searchResultState = searchResultState,
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
    navController.graph.setStartDestination(CharacterDestination.route)
}