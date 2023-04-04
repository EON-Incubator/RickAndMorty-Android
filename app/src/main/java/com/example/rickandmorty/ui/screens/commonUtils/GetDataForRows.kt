package com.example.rickandmorty.ui.screens.commonUtils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

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
    location: Boolean = false,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
//        verticalArrangement = Arrangement.Center
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Text(
                text = titleName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = GetDimensions().mediumPadding, bottom = GetDimensions().mediumPadding),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground
            )
        }
//        Spacer(modifier = Modifier.height(10.dp))
        // var lineHeight = MaterialTheme.typography.body2.fontSize * 4 / 3
        Row(verticalAlignment = Alignment.Bottom) {
            if (property1.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .padding(end = 7.dp, bottom = 7.dp)
                        .weight(1f)
                        .border(
                            shape = RoundedCornerShape(25),
                            border = BorderStroke(1.dp, color = Color.LightGray)
                        )
                        .background(
                            color = colorResource(
                                id = GetColor(
                                    location =
                                    location
                                ).property1
                            ),
                            shape = RoundedCornerShape(25)
                        ),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = property1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, end = 5.dp, top = GetDimensions().xxxSmallPadding, bottom = GetDimensions().xxxSmallPadding),
//                        .sizeIn(
//                            minHeight = with(LocalDensity.current) {
//                                (lineHeight * 2).toDp()
//                            }
//                        ),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }

            if (property2.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .padding(end = 5.dp, bottom = 7.dp)
                        .weight(1f)
                        .border(
                            border = BorderStroke(1.dp, color = Color.LightGray),
                            shape = RoundedCornerShape(25)
                        )
                        .background(
                            color = colorResource(
                                id = GetColor(
                                    location =
                                    location
                                ).property2
                            ),
                            shape = RoundedCornerShape(25)
                        ),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp, top = GetDimensions().xxxSmallPadding, bottom = GetDimensions().xxxSmallPadding),
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