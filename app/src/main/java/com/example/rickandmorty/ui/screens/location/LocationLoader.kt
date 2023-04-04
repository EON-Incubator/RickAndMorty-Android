package com.example.rickandmorty.ui.screens.location

import ExcludeFromJacocoGeneratedReport
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.domain.location.LocationDetail
import com.example.rickandmorty.ui.screens.ScreenType
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithFourImages
import com.example.rickandmorty.ui.screens.commonUtils.shimmerBackground

@ExcludeFromJacocoGeneratedReport
@Composable
fun LocationLoaderCells(deviceType: ScreenType) {
    GetEmptyRow()
    GetEmptyRow()
}

@ExcludeFromJacocoGeneratedReport
@Composable
fun GetEmptyRow() {
    GetRowWithFourImages(
        imageUrlLink = emptyList(),
        titleName = "",
        property1 = "",
        property2 = "",
        onClickable = {},
        id = "",
        modifier = Modifier
            .shimmerBackground(RoundedCornerShape(40.dp))
    )
}

@ExcludeFromJacocoGeneratedReport
@Composable
fun LocationLoader(deviceType: ScreenType) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(
            when (deviceType) {
                ScreenType.PORTRAIT_PHONE -> 1
                else -> 2
            }
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(8.dp)
            .semantics { contentDescription = R.string.fetching_records.toString() }
    ) {
        repeat(
            when (deviceType) {
                ScreenType.PORTRAIT_PHONE -> 8
                ScreenType.LANDSCAPE_PHONE -> 6
                else -> 20
            }
        ) {
            item {
                GetEmptyRow()
            }
        }
    }
}

@ExcludeFromJacocoGeneratedReport
@Composable
fun LocationDetailLoader(deviceType: ScreenType) {
    var list = List(5) {
        DetailedCharacter(
            "",
            "",
            "",
            "",
            "",
            "",
            emptyList(),
            "",
            "",
            "",
            ""
        )
    }
    if (deviceType == ScreenType.PORTRAIT_PHONE) {
        Column(
            modifier = Modifier
                .fillMaxSize().shimmerBackground()
        ) {
            GetInfo(
                LocationDetailViewModel.LocationDetailUiState()
            )
            Spacer(modifier = Modifier.height(30.dp))

            GetResidents(
                LocationDetailViewModel.LocationDetailUiState(
                    LocationDetail(
                        "",
                        "",
                        list,
                        ""
                    ),
                    false
                ),
                {}
            )
        }
    } else if (deviceType == ScreenType.LANDSCAPE_PHONE) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            GetInfo(
                LocationDetailViewModel.LocationDetailUiState(),
                modifier = Modifier
                    .weight(2f)
                    .shimmerBackground()
            )
            GetResidents(
                LocationDetailViewModel.LocationDetailUiState(
                    LocationDetail(
                        "",
                        "",
                        list,
                        ""
                    ),
                    false
                ),
                { },
                modifier = Modifier
                    .weight(5f)
                    .shimmerBackground()
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            GetInfo(
                LocationDetailViewModel.LocationDetailUiState(),
                modifier = Modifier.shimmerBackground()

            )
            GetResidents(
                LocationDetailViewModel.LocationDetailUiState(
                    LocationDetail(
                        "",
                        "",
                        list,
                        ""
                    ),
                    false
                ),
                { },
                fixedElement = 2
            )
        }
    }
}