package com.example.rickandmorty.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithFourImages
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithOneImage
import com.example.rickandmorty.ui.screens.commonUtils.shimmerBackground

@Composable
fun SearchLoader() {
    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Characters",
                Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
                    .shimmerBackground(RoundedCornerShape(40.dp))
            )
        }
        repeat(2) {
            item {
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

        item {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Locations",
                Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .shimmerBackground(RoundedCornerShape(40.dp))
            )
        }

        repeat(2) {
            item {
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