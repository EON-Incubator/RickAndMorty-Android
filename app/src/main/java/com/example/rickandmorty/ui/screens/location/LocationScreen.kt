package com.example.rickandmorty.ui.screens.location

import androidx.compose.runtime.Composable
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithFourImages
import com.example.rickandmorty.ui.screens.commonUtils.ScreenNameBar

object LocationDestination : NavigationDestination {
    override val route = "locations"
    override val screenTitleRes = R.string.locations_screen_title
}

@Composable
fun LocationScreen(
    locationsUiState: LocationViewModel.LocationUiState,
    onClick: (String) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ScreenNameBar(
                name = stringResource(R.string.location),
                onFilterClick = {}
            )

            if (locationsUiState.isLoading) {
            } else {
                LazyColumn() {
                    items(locationsUiState.locations) { location ->

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