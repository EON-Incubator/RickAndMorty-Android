package com.example.rickandmorty.ui.screens.episode

// import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
// import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.Episodes
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.commonUtils.ScreenNameBar

@Composable
fun Episodes() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ScreenNameBar(name = stringResource(R.string.episodes_screen_title), onFilterClick = {})
        }
    }
}

object EpisodeDestination : NavigationDestination {
    override val route = "episodes"
    override val screenTitleRes = R.string.episodes_screen_title
}

@Composable
fun EpisodesScreen(
    state: EpisodeViewModel.EpisodesState,
    onSelectEpisode: (id: String) -> Unit,
    onDismissEpisodeDialog: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
//                items(state.episodes){episode ->
//                    EpisodeItem(
//                        episode = episode,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable { onSelectEpisode }
//                    )
//                }
            }
        }
    }
}

@Composable
private fun EpisodeItem(
    episode: Episodes,
    modifier: Modifier = Modifier,
) {
}