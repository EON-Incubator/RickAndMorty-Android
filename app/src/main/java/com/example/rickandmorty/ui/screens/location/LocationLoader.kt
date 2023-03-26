package com.example.rickandmorty.ui.screens.location

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.ui.screens.ScreenType
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithFourImages
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithOneImage
import com.example.rickandmorty.ui.screens.commonUtils.shimmerBackground

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
        modifier = Modifier.padding(8.dp)
    ) {
        repeat(
            when (deviceType) {
                ScreenType.PORTRAIT_PHONE -> 8
                ScreenType.LANDSCAPE_PHONE -> 6
                else -> 20
            }
        ) {
            item {
                if (deviceType == ScreenType.LANDSCAPE_PHONE) {
                    GetRowWithOneImage(
                        imageUrlLink = "",
                        titleName = "",
                        property1 = "",
                        property2 = "",
                        status = "",
                        id = "",
                        onClickable = {},
                        modifier = Modifier
                            .shimmerBackground(RoundedCornerShape(40.dp))
                    )
                } else {
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
            }
        }
    }
}