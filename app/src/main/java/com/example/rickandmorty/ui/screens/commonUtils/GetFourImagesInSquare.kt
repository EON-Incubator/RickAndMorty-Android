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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
            AsyncImage(
                modifier = Modifier
                    .padding(2.dp)
                    .weight(1f)
                    .size(70.dp)
                    .clip(RoundedCornerShape(5.dp)),
                model = imageUrlLink[0],
                error = painterResource(id = getErrorImage()),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = "Icon of Location Characters"
            )

            AsyncImage(
                modifier = Modifier
                    .padding(2.dp)
                    .weight(1f)
                    .size(70.dp)
                    .clip(RoundedCornerShape(5.dp)),
                error = painterResource(id = getErrorImage()),
                placeholder = painterResource(R.drawable.loading_img),
                model = imageUrlLink[1],
                contentDescription = "Icon of Location Characters"
            )
        }

        Row() {
            AsyncImage(
                modifier = Modifier
                    .padding(2.dp)
                    .weight(1f)
                    .size(70.dp)
                    .clip(RoundedCornerShape(5.dp)),
                error = painterResource(id = getErrorImage()),
                placeholder = painterResource(R.drawable.loading_img),
                model = imageUrlLink[2],
                contentDescription = "Icon of Location Characters"
            )

            AsyncImage(
                modifier = Modifier
                    .padding(2.dp)
                    .weight(1f)
                    .size(70.dp)
                    .clip(RoundedCornerShape(5.dp)),
                error = painterResource(id = getErrorImage()),
                placeholder = painterResource(R.drawable.loading_img),
                model = imageUrlLink[3],
                contentDescription = "Icon of Location Characters"
            )
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