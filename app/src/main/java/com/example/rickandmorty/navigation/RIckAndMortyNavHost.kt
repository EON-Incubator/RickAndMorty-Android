package com.example.rickandmorty.navigation

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmorty.ui.screens.ScreenType
import com.example.rickandmorty.ui.screens.character.*
import com.example.rickandmorty.ui.screens.episode.*
import com.example.rickandmorty.ui.screens.location.*
import com.example.rickandmorty.ui.screens.search.Search
import com.example.rickandmorty.ui.screens.search.SearchViewModel

@ExperimentalMaterialApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickAndMortyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onDetailScreen: (Boolean) -> Unit,
    deviceType: ScreenType,
) {
    val viewModel = hiltViewModel<SearchViewModel>()
    val searchResultState by viewModel.searchResult.collectAsState()
    val scope = rememberCoroutineScope()

    var showCharacters by remember {
        mutableStateOf(true)
    }
    var showLocations by remember {
        mutableStateOf(true)
    }
    val searchListState = rememberLazyListState()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = CharacterDestination.route
    ) {
        composable(CharacterDestination.route) {
            onDetailScreen(false)
            val viewModel = hiltViewModel<CharacterViewModel>()
            val characterState by viewModel.characters.collectAsState()
            val listState = rememberLazyGridState()
            val refreshState by viewModel.isRefreshing.collectAsState()

            val endReached by remember {
                derivedStateOf {
                    listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ==
                        listState.layoutInfo.totalItemsCount - 1
                }
            }
            if (endReached) {
                viewModel.updateList()
            }

            Characters(
                characterState,
                genderVal = viewModel.gender,
                statusVal = viewModel.status,
                onClick = {
                    navController.navigate(CharacterDetailsDestination.route + "?id=$it")
                },
                listState = listState,
                applyFilter = { viewModel.selectFilter() },
                changeGender = { viewModel.changeGender(it) },
                changeStatus = { viewModel.changeStatus(it) },
                isRefreshing = refreshState,
                onRefresh = {
                    viewModel.refresh()
                }

            )
        }
        composable(CharacterDetailsDestination.route + "?id={id}") {
            onDetailScreen(true)
            val characterDetailViewModel = hiltViewModel<DetailedCharacterViewModel>()

            val characterState by characterDetailViewModel.character.collectAsState()
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
                },
                deviceType = deviceType

            )
        }
        composable(EpisodeDestination.route) {
            onDetailScreen(true)
            val episodeViewModel = hiltViewModel<EpisodeViewModel>()
            val state by episodeViewModel.state.collectAsState()
            val listState = rememberLazyGridState()
            val refreshState by episodeViewModel.isRefreshing.collectAsState()

            val endReached by remember {
                derivedStateOf {
                    listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ==
                        listState.layoutInfo.totalItemsCount - 1
                }
            }
            if (endReached) {
                episodeViewModel.updateEpisodeList()
            }

            EpisodesScreen(
                state = state,
                onSelectEpisode = {
                    navController.navigate(EpisodeDetailsDestination.route + "?id=$it")
                },
                onRefresh = { episodeViewModel.refresh() },
                listState = listState,
                deviceType = deviceType,
                isRefreshing = refreshState
            )
        }
        composable(EpisodeDetailsDestination.route + "?id={id}") {
            onDetailScreen(true)
            val episodeDetailViewModel = hiltViewModel<EpisodeDetailViewModel>()
            val state by episodeDetailViewModel.state.collectAsState()
            val episodeDetails by episodeDetailViewModel.episodeDetail.collectAsState()
            EpisodeDetails(
                state = state,
                episodeDetails = episodeDetails,
                navigateUp = { navController.popBackStack() },
                onCharacterClick = {
                    navController.navigate(CharacterDetailsDestination.route + "?id=$it")
                },
                deviceType = deviceType,
                episodeDetailViewModel = episodeDetailViewModel

            )
        }
        composable(LocationDestination.route) {
            onDetailScreen(true)
            val locationViewModel = hiltViewModel<LocationViewModel>()
            val locationsState by locationViewModel.location.collectAsState()
            val refreshState by locationViewModel.isRefreshing.collectAsState()
            val listState = rememberLazyGridState()

            val endReached by remember {
                derivedStateOf {
                    listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ==
                        listState.layoutInfo.totalItemsCount - 1
                }
            }
            if (endReached) {
                locationViewModel.updateList()
            }

            LocationScreen(
                locationsState,
                onClick = {
                    navController.navigate(LocationDetailsDestination.route + "?id=$it")
                },
                onRefresh = {
                    locationViewModel.refresh()
                },
                listState = listState,
                deviceType = deviceType,
                isRefreshing = refreshState
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
                },
                deviceType = deviceType

            )
        }
        composable("search") {
            Search(
                searchResultState = searchResultState,
                onValueChange = { viewModel.onSearch(it) },
                query = viewModel.query.collectAsState(),
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
                showLocations = showLocations,
                updateCharacterList = { viewModel.updateCharacterList() },
                updateLocationList = { viewModel.updateLocationList() },
                searchListState = searchListState,
                deviceType = deviceType,
                onResetQuery = { viewModel.onResetQuery() }
            )
        }
    }
    navController.graph.setStartDestination(CharacterDestination.route)
}