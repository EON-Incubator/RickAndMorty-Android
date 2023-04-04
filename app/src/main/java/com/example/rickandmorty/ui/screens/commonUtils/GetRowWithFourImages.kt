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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
        elevation = GetElevation().medium,
        modifier = Modifier
            .padding(start = GetPadding().xxxMediumPadding, end = GetPadding().xxMediumPadding, bottom = GetPadding().xMediumPadding)
            .semantics { contentDescription = R.string.four_image_row.toString() }
            .clickable {
                onClickable(id)
            },
        backgroundColor = colorResource(id = GetColor(location = location).card_background)

    ) {
        Row(
            modifier = Modifier.padding(GetPadding().xxSmallPadding),
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
                    modifier = Modifier.size(dimensionResource(id = R.dimen.four_image_size)),
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_forward_ios_24),
                    contentDescription = stringResource(R.string.go_to_detail_screen)

                )
            }
        }
    }
}