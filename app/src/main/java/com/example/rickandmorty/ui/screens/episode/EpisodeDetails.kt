package com.example.rickandmorty.ui.screens.episode

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.navigation.NavigationDestination

@Composable
fun EpisodeDetails() {
    val viewModel = hiltViewModel<EpisodeViewModel>()
    val state by viewModel.state.collectAsState()
}

object EpisodeDetailsDestination : NavigationDestination {
    override val route = "episode_detail"
    override val screenTitleRes = R.string.episode_detail_screen_title
}

@Composable
fun EpisodeDetailScreen(
    state: EpisodeViewModel.EpisodesState,
    episode: DetailedEpisode,
) {
    if (state.selectedEpisode != null) {
        Text(
            text = "INFO",
            fontSize = 12.sp
        )
        Column(modifier = Modifier) {
            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "EPISODE HERE",
                    fontSize = 12.sp
                )
            }
            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "AIR DATE HERE",
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.width(40.dp))

        Text(
            text = "CHARACTERS",
            fontSize = 12.sp
        )
    }

    Text(
        text = "INFO",
        fontSize = 12.sp
    )
    Column(modifier = Modifier) {
        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text = "EPISODE HERE",
                fontSize = 12.sp
            )
        }
        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text = "AIR DATE HERE",
                fontSize = 12.sp
            )
        }
    }

    Spacer(modifier = Modifier.width(40.dp))

    Text(
        text = "CHARACTERS",
        fontSize = 12.sp
    )

//    LazyColumn() {
//        items() { episode ->
//
//        }
//
//        Card(
//            modifier = Modifier.fillMaxWidth()
//        ) {
//
//
//
//        }
//
//    }
}