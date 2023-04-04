package com.example.rickandmorty.ui.screens.commonUtils

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Composable function that draws row with an icon
 * which is an image vector and row with
 * 2 properties on screens
 * Eg: Rows in Character Detail Screen Screen
 **/
@Composable
fun GetInfoInLine(
    icons: ImageVector,
    topic: String,
    topicAnswer: String,
    modifier: Modifier = Modifier,
    showIt: String? = null,
    action: () -> Unit = {},
    iconArrow: ImageVector? = null,
    location: Boolean = false,
) {
    if (topicAnswer.isNotEmpty()) {
        Card(
            shape = RoundedCornerShape(CornerSize(4.dp)),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 10.dp),
            backgroundColor = colorResource(id = GetColor(location = location).detail_info_card)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, bottom = 7.dp, top = 7.dp, end = 7.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier
                        .padding(end = 7.dp)
                        .size(23.dp),
                    imageVector = icons,
                    contentDescription = "Icon"
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = topic,
                    maxLines = 1,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.weight(3f),
                    text = topicAnswer,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Normal,
                    maxLines = 2,
                    textAlign = TextAlign.End
                )

                iconArrow?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "to go on next location screen"
                    )
                }
            }
//        Divider(
//            Modifier.height(1.dp),
//            color = MaterialTheme.colors.onBackground
//        )
        }
    }
}