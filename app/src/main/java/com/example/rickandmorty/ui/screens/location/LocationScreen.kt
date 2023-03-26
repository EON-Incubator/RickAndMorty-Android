package com.example.rickandmorty.ui.screens.location

import androidx.compose.runtime.Composable
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.ui.screens.ScreenType
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithFourImages
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithOneImage
import com.example.rickandmorty.ui.screens.commonUtils.ScreenNameBar

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
@Composable
fun LocationScreen(
    locationsUiState: LocationViewModel.LocationUiState,
    onClick: (String) -> Unit,
    listState: LazyGridState,
    deviceType: ScreenType,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.fillMaxSize().semantics { contentDescription = "Locations" }) {
            if (deviceType != ScreenType.LANDSCAPE_PHONE) {
                ScreenNameBar(
                    name = stringResource(R.string.location),
                    onFilterClick = {}
                )
            }
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
                            GetRowWithOneImage(
                                imageUrlLink =
                                if (location.images?.isEmpty() == true) { "" } else { location.images?.get(0) }
                                    .toString(),
                                titleName = location.name.toString(),
                                property1 = location.type.toString(),
                                property2 = location.dimension.toString(),
                                status = "",
                                id = location.id.toString(),
                                onClickable = onClick
                            )
                        } else {
                            GetRowWithFourImages(
                                imageUrlLink = location.images,
                                titleName = location.name.toString(),
                                property1 = location.type.toString(),
                                property2 = location.dimension.toString(),
                                onClickable =
                                onClick,
                                id = location.id.toString()
                            )
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