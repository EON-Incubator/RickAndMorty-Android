package com.example.rickandmorty.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.ui.screens.commonUtils.GetDimensions
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithFourImages
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithOneImage
import com.example.rickandmorty.ui.screens.commonUtils.shimmerBackground

@Composable
fun SearchLoader() {
    Column {
        Column() {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Characters",
                Modifier
                    .fillMaxWidth()
                    .padding(GetDimensions().xxxSmallPadding)
                    .shimmerBackground(RoundedCornerShape(40.dp))
                    .semantics { contentDescription = "Fetching Records" }
            )
        }
        repeat(2) {
            Column() {
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
            }
        }

        Column() {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Locations",
                Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .padding(GetDimensions().xxxSmallPadding)
                    .shimmerBackground(RoundedCornerShape(40.dp))
            )
        }

        repeat(2) {
            Column() {
                GetRowWithFourImages(
                    imageUrlLink = emptyList(),
                    titleName = "",
                    property1 = "",
                    property2 = "",
                    id = "",
                    onClickable = { },
                    modifier = Modifier
                        .shimmerBackground(RoundedCornerShape(40.dp))
                )
            }
        }
    }
}