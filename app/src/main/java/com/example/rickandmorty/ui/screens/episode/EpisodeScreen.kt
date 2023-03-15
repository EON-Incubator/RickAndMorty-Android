package com.example.rickandmorty.ui.screens.episode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.Episodes
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithFourImages
import com.example.rickandmorty.ui.screens.commonUtils.ScreenNameBar

@Composable
fun Episodes() {
    val viewModel = hiltViewModel<EpisodeViewModel>()
    val state by viewModel.state.collectAsState()
}

object EpisodeDestination : NavigationDestination {
    override val route = "episodes"
    override val screenTitleRes = R.string.episodes_screen_title
}

@Composable
fun EpisodesScreen(
    state: EpisodeViewModel.EpisodesState,
    onSelectEpisode: (id: String?) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ScreenNameBar(
                name = stringResource(R.string.episodes_screen_title),
                onFilterClick = {}
            )

            if (state.isLoading) {
            } else {
                LazyColumn() {
                    items(state.episodes) { episode ->
                        GetRowWithFourImages(
                            imageUrlLink = episode.images,
                            titleName = episode.name.toString(),
                            property1 = episode.episode.toString(),
                            property2 = episode.air_date.toString(),
                            onClickable = { onSelectEpisode(episode.id.toString()) },
                            id = episode.id.toString()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EpisodeDetail(
    episode: Episodes,
    modifier: Modifier = Modifier,
) {
}

@Composable
fun EpisodeDialog(
    episode: DetailedEpisode,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {}