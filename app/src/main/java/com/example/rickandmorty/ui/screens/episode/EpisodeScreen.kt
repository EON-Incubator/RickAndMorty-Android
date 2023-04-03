package com.example.rickandmorty.ui.screens.episode

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.ScreenType
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithFourImages
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithOneImage
import com.example.rickandmorty.ui.screens.commonUtils.ScreenNameBar
import com.example.rickandmorty.ui.screens.location.LocationLoader
import com.example.rickandmorty.ui.screens.location.LocationLoader2
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
    listState: LazyGridState,
    deviceType: ScreenType = ScreenType.PORTRAIT_PHONE,
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit = {},
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .semantics { contentDescription = "Fetching Episodes" }
        ) {
            if (deviceType != ScreenType.LANDSCAPE_PHONE) {
                ScreenNameBar(
                    name = stringResource(R.string.episodes_screen_title),
                    onFilterClick = {}
                )
            }
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = onRefresh,
                indicator = { state, refreshTrigger ->
                    SwipeRefreshIndicator(
                        state = state,
                        refreshTriggerDistance = refreshTrigger,
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.onPrimary
                    )
                }
            ) {
                if (state.isLoadingPage) {
                    LocationLoader(deviceType)
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(
                            when (deviceType) {
                                ScreenType.PORTRAIT_PHONE -> 1
                                else -> 2
                            }
                        ),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(8.dp),
                        state = listState
                    ) {
                        items(state.episodes) { episode ->
                            if (deviceType == ScreenType.LANDSCAPE_PHONE) {
                                GetRowWithOneImage(
                                    imageUrlLink =
                                    if (episode.images?.isEmpty() == true) {
                                        ""
                                    } else {
                                        episode.images?.get(0)
                                    }
                                        .toString(),
                                    titleName = episode.name.toString(),
                                    property1 = episode.episode.toString(),
                                    property2 = episode.air_date.toString(),
                                    status = "",
                                    id = episode.id.toString(),
                                    onClickable = onSelectEpisode,
                                    icons = listOf(
                                        ImageVector.vectorResource(id = R.drawable.episode),
                                        ImageVector.vectorResource(id = R.drawable.date)
                                    )
                                )
                            } else {
                                GetRowWithFourImages(
                                    imageUrlLink = episode.images,
                                    titleName = episode.name.toString(),
                                    property1 = episode.episode.toString(),
                                    property2 = episode.air_date.toString(),
                                    onClickable =
                                    onSelectEpisode,
                                    id = episode.id.toString(),
                                    icons = listOf(
                                        ImageVector.vectorResource(id = R.drawable.episode),
                                        ImageVector.vectorResource(id = R.drawable.date)
                                    )
                                )
                            }
                        }
                        item {
                            if (state.isLoading) {
                                LocationLoader2(deviceType)
                            }
                        }
                    }
                }
            }
        }
    }
}