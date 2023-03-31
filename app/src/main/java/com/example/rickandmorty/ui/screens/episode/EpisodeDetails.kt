package com.example.rickandmorty.ui.screens.episode

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.RickAndMortyTopAppBar
import com.example.rickandmorty.ui.screens.ScreenType
import com.example.rickandmorty.ui.screens.commonUtils.GetInfoInLine
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithOneImage
import com.example.rickandmorty.ui.screens.commonUtils.shimmerBackground

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EpisodeDetails(
    state: EpisodeDetailViewModel.DetailEpisodesState,
    navigateUp: () -> Unit,
    onCharacterClick: (String) -> Unit,
    deviceType: ScreenType = ScreenType.PORTRAIT_PHONE,
) {
    Scaffold(topBar = {
        if (state.isLoading) {
            RickAndMortyTopAppBar(
                title = stringResource(R.string.loading),
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        } else {
            RickAndMortyTopAppBar(
                title = state.selectedEpisode?.name.toString(),
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        }
    }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .semantics { contentDescription = "EP Detail" }
            ) {
                if (state.isLoading) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .semantics { contentDescription = "Episode Detail Load" },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column() {
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = stringResource(R.string.info),
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .padding(start = 10.dp)
                            )

                            Spacer(modifier = Modifier.height(8.dp))


                            GetInfoInLine(
                                icons = ImageVector.vectorResource(id = R.drawable.episode),
                                topic = stringResource(id = R.string.episode),
                                topicAnswer = stringResource(R.string.loading)
                            )

                            Row() {
                                GetInfoInLine(
                                    icons = ImageVector.vectorResource(id = R.drawable.date),
                                    topic = stringResource(id = R.string.air_date),
                                    topicAnswer = stringResource(R.string.loading)
                                )
                            }

                            Spacer(modifier = Modifier.height(40.dp))

                            Text(
                                text = stringResource(R.string.characters),
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .padding(start = 10.dp)
                            )

                            LazyColumn() {
                                repeat(4) {
                                    item {
                                        GetRowWithOneImage(
                                            imageUrlLink = "",
                                            titleName = "",
                                            property1 = "",
                                            property2 = "",
                                            status = "",
                                            id = "",
                                            onClickable = {},
                                            modifier = Modifier.shimmerBackground(RoundedCornerShape(40.dp))
                                        )
                                    }
                                }
                            }
                        }
                    }
                } else if (state.selectedEpisode != null) {
                    if (deviceType == ScreenType.PORTRAIT_PHONE) {
                        Column() {
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = stringResource(R.string.info),
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .padding(start = 10.dp)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            GetInfoInLine(
                                icons = ImageVector.vectorResource(id = R.drawable.episode),
                                topic = stringResource(id = R.string.episode),
                                topicAnswer = state.selectedEpisode?.episode.toString()
                            )

                            Row() {
                                GetInfoInLine(
                                    icons = ImageVector.vectorResource(id = R.drawable.date),
                                    topic = stringResource(id = R.string.air_date),
                                    topicAnswer = state.selectedEpisode?.air_date.toString()
                                )
                            }


                            Spacer(modifier = Modifier.height(40.dp))

                            Text(
                                text = stringResource(R.string.characters),
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .padding(start = 10.dp)
                            )

                            if (state.selectedEpisode.characters.isNotEmpty()) {
                                Log.v("character", state.characters.toString())
                                LazyColumn() {
                                    items(state.selectedEpisode.characters) { episode ->
                                        GetRowWithOneImage(
                                            imageUrlLink = episode.image.toString(),
                                            titleName = episode.name.toString(),
                                            property1 = episode.gender.toString(),
                                            property2 = episode.species.toString(),
                                            status = episode.status.toString(),
                                            id = episode.ID.toString(),
                                            onClickable = {
                                                onCharacterClick(it)
                                            }
                                        )
                                    }
                                }
                            } else {
                                ImageVector.vectorResource(id = R.drawable.ic_broken_image)
                            }
                        }
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                        ) {
                            Column(modifier = Modifier.weight(2F)) {
                                Text(
                                    text = stringResource(R.string.info),
                                    fontSize = 12.sp,
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                )

                                Spacer(modifier = Modifier.height(6.dp))


                                GetInfoInLine(
                                    icons = ImageVector.vectorResource(id = R.drawable.episode),
                                    topic = stringResource(id = R.string.episode),
                                    topicAnswer = state.selectedEpisode?.episode.toString()
                                )

                                Row() {
                                    GetInfoInLine(
                                        icons = ImageVector.vectorResource(id = R.drawable.date),
                                        topic = stringResource(id = R.string.air_date),
                                        topicAnswer = state.selectedEpisode?.air_date.toString()

                                    )
                                }


                                Spacer(modifier = Modifier.height(40.dp))
                            }

                            Column(modifier = Modifier.weight(5F)) {
                                Text(
                                    text = stringResource(R.string.characters),
                                    fontSize = 12.sp,
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                )

                                if (state.selectedEpisode.characters.isNotEmpty()) {
                                    Log.v("character", state.characters.toString())
                                    LazyColumn() {
                                        items(state.selectedEpisode.characters) { episode ->
                                            GetRowWithOneImage(
                                                imageUrlLink = episode.image.toString(),
                                                titleName = episode.name.toString(),
                                                property1 = episode.gender.toString(),
                                                property2 = episode.species.toString(),
                                                status = episode.status.toString(),
                                                id = episode.ID.toString(),
                                                onClickable = {
                                                    onCharacterClick(it)
                                                }
                                            )
                                        }
                                    }
                                } else {
                                    ImageVector.vectorResource(id = R.drawable.ic_broken_image)
                                }
                            }
                        }
                    }
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_broken_image),
                        contentDescription = "Broken"
                    )
                }
            }
        }
    }
}

object EpisodeDetailsDestination : NavigationDestination {
    override val route = "episode_detail"
    override val screenTitleRes = R.string.episode_detail_screen_title
}