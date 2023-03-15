package com.example.rickandmorty.ui.screens.episode

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.navigation.NavigationDestination

@Composable
fun EpisodeDetails(state: EpisodeDetailViewModel.DetailEpisodesState) {
    Column() {
        if (state.isLoading) {
        } else if (state.selectedEpisode != null) {
            Column() {
//                Text(text = state.selectedEpisode?.id.toString())

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "INFO",
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Divider(
                    Modifier.height(5.dp)
                )
                Column() {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(start = 40.dp)
                                .weight(1f)
                        ) {
//                            Image(painter = painterResource(id = R.drawable.episode), contentDescription = "episode")
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Episode",
                                fontSize = 12.sp
//                            modifier = Modifier
//                                .padding(start = 4.dp, top = 12.dp, bottom = 4.dp)
                            )
                        }

                        Row(
                            modifier = Modifier
                                .padding(start = 40.dp)
                                .weight(1f)
                        ) {
                            Text(
                                text = state.selectedEpisode?.episode.toString(),
                                fontSize = 12.sp
//                            modifier = Modifier
//                                .padding(top = 12.dp, bottom = 4.dp, end = 4.dp)
                            )
                        }
                    }

                    Divider(
                        Modifier.height(5.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(start = 40.dp)
                                .weight(1f)
                        ) {
//                            Image(painter = painterResource(id = R.drawable.air_date), contentDescription = "air date")

                            Spacer(modifier = Modifier.width(5.dp))

                            Text(
                                text = "Air Date",
                                fontSize = 12.sp
//                            modifier = Modifier
//                                .padding(start = 4.dp, top = 12.dp, bottom = 4.dp)
                            )
                        }

                        Row(
                            modifier = Modifier
                                .padding(start = 50.dp)
                                .weight(1f)
                        ) {
                            Text(
                                text = state.selectedEpisode?.air_date.toString(),
                                fontSize = 12.sp
                            )
                        }
                    }

                    Divider(
                        Modifier.height(5.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "CHARACTERS",
                    fontSize = 12.sp
                )
            }
        } else {
        }
    }
}

object EpisodeDetailsDestination : NavigationDestination {
    override val route = "episode_detail"
    override val screenTitleRes = R.string.episode_detail_screen_title
}

@Composable
fun EpisodeDetailScreen(
    state: EpisodeDetailViewModel.DetailEpisodesState,
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