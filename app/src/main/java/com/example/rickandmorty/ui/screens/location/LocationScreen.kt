package com.example.rickandmorty.ui.screens.location

import ExcludeFromJacocoGeneratedReport
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.ScreenType
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithFourImages
import com.example.rickandmorty.ui.screens.commonUtils.ScreenNameBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/**
 * Defining the route for the LocationScreen
 */
object LocationDestination : NavigationDestination {
    override val route = "locations"
    override val screenTitleRes = R.string.locations_screen_title
}

/**
 * Function that displays all the components on the LocationScreen Tab
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LocationScreen(
    locationsUiState: LocationViewModel.LocationUiState,
    onRefresh: () -> Unit = {},
    onClick: (String) -> Unit,
    listState: LazyGridState,
    deviceType: ScreenType = ScreenType.PORTRAIT_PHONE,
    isRefreshing: Boolean = false,
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
                .semantics { contentDescription = "Locations" }
        ) {
            if (deviceType != ScreenType.LANDSCAPE_PHONE) {
                ScreenNameBar(
                    name = stringResource(R.string.location),
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
                if (locationsUiState.isLoading) {
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
                        items(locationsUiState.locations) { location ->

                            // Method in CommonUtils that draws the Card with 4 Images

                            if (deviceType == ScreenType.LANDSCAPE_PHONE) {
                                GetRowWithFourImages(
                                    imageUrlLink = location.images,
                                    titleName = location.name.toString(),
                                    property1 = location.type.toString(),
                                    property2 = location.dimension.toString(),
                                    id = location.id.toString(),
                                    onClickable = onClick,
                                    icons = listOf(
                                        ImageVector.vectorResource(id = R.drawable.type),
                                        ImageVector.vectorResource(id = R.drawable.dimension)
                                    )
                                )
                            } else {
                                GetRowWithFourImages(
                                    imageUrlLink = location.images,
                                    titleName = location.name.toString(),
                                    property1 = location.type.toString(),
                                    property2 = location.dimension.toString(),
                                    onClickable =
                                    onClick,
                                    id = location.id.toString(),
                                    icons = listOf(
                                        ImageVector.vectorResource(id = R.drawable.type),
                                        ImageVector.vectorResource(id = R.drawable.dimension)
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * View Location Screen In Light Mode
 */
@ExcludeFromJacocoGeneratedReport
@Composable
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
fun LocationScreenPreviewLightMode() {
    GetRowWithFourImages(
        mutableListOf(
            "https://" +
                "rickandmortyapi.com/api/character/avatar/10.jpeg",
            "https://rickandmortyapi.com/api/character/avatar/10.jpeg"
        ),
        "Anatomy Park With B",
        "Mount",
        "Evil Dimention",
        {},
        "1"
    )
}

/**
 * View Location Screen In Dark Mode
 */
@ExcludeFromJacocoGeneratedReport
@Composable
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
fun LocationScreenPreviewDarkMode() {
    GetRowWithFourImages(
        mutableListOf(
            "https://rickandmortyapi.com/api/character/avatar/10.jpeg",
            "https://rickandmortyapi.com/api/character/avatar/10.jpeg"
        ),
        "Anatomy Park With B",
        "Mount",
        "Evil Dimention",
        {},
        "1"
    )
}