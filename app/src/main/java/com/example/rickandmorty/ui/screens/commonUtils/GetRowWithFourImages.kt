package com.example.rickandmorty.ui.screens.commonUtils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R

/**
 * Composable function that draws a card with 4 images
 * and some data
 * Eg: Location Screen and Episode Screen
 **/
@Composable
fun GetRowWithFourImages(
    imageUrlLink: List<String>?,
    titleName: String,
    property1: String,
    property2: String,
    onClickable: (String) -> Unit,
    id: String,
    icons: List<ImageVector> = emptyList(),
    modifier: Modifier = Modifier,
    location: Boolean = false,
) {
    var mutableImageLink = imageUrlLink!!.toMutableList()

    if (mutableImageLink.size < 4) {
        for (i in mutableImageLink.size..3) {
            mutableImageLink.add("")
        }
    }

    Card(
        shape = RoundedCornerShape(10),
        elevation = 7.dp,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
            .semantics { contentDescription = "Four Image Row" }
            .clickable {
                onClickable(id)
            },
        backgroundColor = colorResource(id = GetColor(location = location).card_background)

    ) {
        Row(
            modifier = Modifier.padding(3.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = modifier.weight(5f)) {
                GetImages(mutableImageLink)
            }

            Row(modifier = modifier.weight(14f)) {
                GetData(
                    titleName,
                    property1,
                    property2,
                    icons,
                    location
                )
            }
            Row(modifier = modifier.weight(1f)) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_forward_ios_24),
                    contentDescription = "Click to go to Detail Screen"

                )
            }
        }
    }
}