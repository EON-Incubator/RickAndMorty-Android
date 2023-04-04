package com.example.rickandmorty.ui.screens.commonUtils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import com.example.rickandmorty.R

/**
 * Composable function that shows the screen bar with the
 * screen title
 * Eg: Location, Episode, Character
 */
@Composable
fun ScreenNameBar(
    modifier: Modifier = Modifier,
    name: String,
    onFilterClick: () -> Unit,
    putIcon: Boolean = false,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(GetPadding().xxSmallPadding, bottom = GetPadding().xSmallPadding)
            .height(dimensionResource(id = R.dimen.screen_name_bar_height))
            .padding(GetPadding().xSmallPadding)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(start = GetPadding().xxxMediumPadding)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = GetPadding().xxxMediumPadding),
            horizontalArrangement = Arrangement.End
        ) {
            if (putIcon) {
                IconButton(
                    onClick = { onFilterClick() }
                ) {
                    Card(
                        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.screen_name_bar_width))),
                        modifier = Modifier.width(dimensionResource(id = R.dimen.screen_name_bar_width))
                    ) {
                        Row(
                            modifier = Modifier.background(
                                Color.LightGray
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(dimensionResource(id = R.dimen.screen_name_bar_icon_size)),
                                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_filter_alt_24),
                                contentDescription = "Filter",
                                tint = Color.Black
                            )
                            Text(
                                text = "Filter",
                                modifier = Modifier.padding(start = GetPadding().smallPadding),
                                style = MaterialTheme.typography.body1,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}