package com.example.rickandmorty.ui.screens.commonUtils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.rickandmorty.R

/**
 * Helper Method to draw images from
 * list of Strings that has links
 * to the images
 **/
@Composable
fun GetImages(imageUrlLink: MutableList<String>) {
    Column() {
        Row() {
            GetSingleImage(imageUrlLink[0])
            GetSingleImage(imageUrlLink[1])
        }

        Row() {
            GetSingleImage(imageUrlLink[2])
            GetSingleImage(imageUrlLink[3])
        }
    }
}

@Composable
fun getErrorImage() =
    if (isSystemInDarkTheme()) {
        R.drawable.person_image_in_dark
    } else {
        R.drawable.person_image
    }

@Composable
fun GetSingleImage(imageUrlLink: String) {
    AsyncImage(
        modifier = Modifier
            .padding(GetPadding().xxxSmallPadding)
            .size(dimensionResource(id = R.dimen.four_image_size))
            .clip(RoundedCornerShape(15)),
        model = imageUrlLink,
        contentScale = ContentScale.Crop,
        error = painterResource(id = getErrorImage()),
        placeholder = painterResource(R.drawable.loading_img),
        contentDescription = "Icon of Location Characters"
    )
}