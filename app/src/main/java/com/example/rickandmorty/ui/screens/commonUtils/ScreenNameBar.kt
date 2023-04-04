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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
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
            .padding(3.dp, bottom = 6.dp)
            .height(45.dp)
            .padding(5.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(start = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            if (putIcon) {
                IconButton(
                    onClick = { onFilterClick() }
                ) {
                    Card(
                        shape = RoundedCornerShape(CornerSize(100.dp)),
                        modifier = Modifier.width(100.dp)
                    ) {
                        Row(
                            modifier = Modifier.background(
                                Color.LightGray
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(27.dp),
                                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_filter_alt_24),
                                contentDescription = "Filter",
                                tint = Color.Black
                            )
                            Text(
                                text = "Filter",
                                modifier = Modifier.padding(start = 8.dp),
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