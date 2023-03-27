package com.example.rickandmorty.ui.screens.episode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.ScreenType
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithFourImages
import com.example.rickandmorty.ui.screens.commonUtils.ScreenNameBar
import com.example.rickandmorty.ui.screens.commonUtils.shimmerBackground
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

object EpisodeDestination : NavigationDestination {
    override val route = "episodes"
    override val screenTitleRes = R.string.episodes_screen_title
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EpisodesScreen(
    state: EpisodeViewModel.EpisodesState,
    onSelectEpisode: (id: String?) -> Unit,
    listState: LazyListState,
    device: ScreenType? = ScreenType.PORTRAIT_PHONE
) {
    val viewModel: EpisodeViewModel = hiltViewModel()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {


        Column(modifier = Modifier
            .fillMaxSize()
            .semantics { contentDescription = "Fetching Episodes" }
        ) {
            ScreenNameBar(
                name = stringResource(R.string.episodes_screen_title),
                onFilterClick = {}
            )
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { viewModel.updateEpisodeList() },
                indicator = { state, refreshTrigger ->
                    SwipeRefreshIndicator(
                        state = state,
                        refreshTriggerDistance = refreshTrigger,
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.onPrimary
                    )
                }
            ) {

                if (state.isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .semantics { contentDescription = "Fetching Records" }
                            )
                            LazyColumn(
                                state = listState
                            ) {
                                items(state.episodes) { episode ->
                                    GetRowWithFourImages(
                                        imageUrlLink = emptyList(),
                                        titleName = "",
                                        property1 = "",
                                        property2 = "",
                                        onClickable = { },
                                        id = "",
                                        modifier = Modifier.shimmerBackground(RoundedCornerShape(40.dp))
                                    )
                                }
                            }
                        }
                    }
                } else {
                    LazyColumn(
                        state = listState
                    ) {
                        items(state.episodes) { episode ->
                            GetRowWithFourImages(
                                imageUrlLink = episode.images,
                                titleName = episode.name.toString(),
                                property1 = episode.episode.toString(),
                                property2 = episode.air_date.toString(),
                                onClickable = { onSelectEpisode(episode.id.toString()) },
                                id = episode.id.toString(),
                            )
                        }
                    }
                }

            }



        }
    }
}