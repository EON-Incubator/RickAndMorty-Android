package com.example.rickandmorty.ui.screens.episode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.Episodes
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.commonUtils.ScreenNameBar

@Composable
fun Episodes() {
    val viewModel = hiltViewModel<EpisodeViewModel>()
    val state by viewModel.state.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ScreenNameBar(name = stringResource(R.string.episodes_screen_title), onFilterClick = {})
//            EpisodesScreen(
//                state = state,
//                onSelectEpisode = viewModel::selectEpisode,
//                onDismissEpisodeDialog = viewModel::dismissEpisodeDialog
//            )
        }
    }
}

object EpisodeDestination : NavigationDestination {
    override val route = "episodes"
    override val screenTitleRes = R.string.episodes_screen_title
}

// @Composable
// fun EpisodesScreen(
//    state: EpisodeViewModel.EpisodesState,
// //    onSelectEpisode: (id: String) -> Unit,
// //    onDismissEpisodeDialog: () -> Unit,
// ) {
//    Box(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        if (state.isLoading) {
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.Center)
//            )
//        } else {
//            LazyColumn(
//                modifier = Modifier.fillMaxSize()
//            ) {
//                items(state.episodes) { episode ->
//                    EpisodeItem(
//                        episode = episode,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable { onSelectEpisode(episode.episode!!) }
//                            .padding(16.dp)
//                    )
//                }
//            }
//        }
//
//        if (state.selectedEpisode != null) {
//            EpisodeDialog(
//                episode = state.selectedEpisode,
//                onDismiss = onDismissEpisodeDialog,
//                modifier = Modifier
//                    .clip(RoundedCornerShape(5.dp))
//                    .background(color = Color.White)
//                    .padding(16.dp)
//            )
//        }
//    }
// }

@Composable
fun EpisodeDialog(
    episode: DetailedEpisode,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {}

@Composable
private fun EpisodeItem(
    episode: Episodes,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = episode.images.toString(),
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = episode.name!!,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = episode.episode!!
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = episode.air_date!!
            )
        }
    }
}