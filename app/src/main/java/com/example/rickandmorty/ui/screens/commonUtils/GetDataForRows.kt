package com.example.rickandmorty.ui.screens.commonUtils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R

/**
 * Helper composable function that draws the data on
 * the screen
 * Eg: Location Screen data embedded in the card with 4 images
 **/
@Composable
fun GetData(
    titleName: String,
    property1: String,
    property2: String,
    icons: List<ImageVector> = emptyList(),
    property1_color: Int = R.color.white,
    property2_color: Int = R.color.white,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = titleName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground
        )
        var lineHeight = MaterialTheme.typography.body2.fontSize * 4 / 3
        Row() {
            Row(
                modifier = Modifier
                    .padding(start = 15.dp, end = 7.dp, bottom = 7.dp)
                    .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (icons.isNotEmpty()) {
                    Icon(
                        modifier = Modifier
                            .padding(end = 7.dp)
                            .size(23.dp),
                        imageVector = icons[0],
                        contentDescription = "Icon"
                    )
                }
                Card(
                    shape = RoundedCornerShape(CornerSize(12.dp)),
                    modifier = Modifier.width(170.dp)
                ) {
                    Text(
                        text = property1,
                        modifier = Modifier
                            .sizeIn(
                                minHeight = with(LocalDensity.current) {
                                    (lineHeight * 2).toDp()
                                }
                            )
                            .background(colorResource(id = property1_color)),
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(end = 15.dp, bottom = 7.dp)
                    .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (icons.isNotEmpty()) {
                    Icon(
                        modifier = Modifier
                            .padding(end = 7.dp)
                            .size(23.dp),
                        imageVector = icons[1],
                        contentDescription = "Icon"
                    )
                }
                Card(
                    shape = RoundedCornerShape(CornerSize(12.dp)),
                    modifier = Modifier.width(170.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .sizeIn(
                                minHeight = with(LocalDensity.current) {
                                    (lineHeight * 2).toDp()
                                }
                            )
                            .background(colorResource(id = property2_color)),
                        textAlign = TextAlign.Center,
                        text = property2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }
}