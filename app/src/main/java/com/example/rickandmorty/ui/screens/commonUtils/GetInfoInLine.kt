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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R

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
            shape = RoundedCornerShape(CornerSize(GetThickness().small)),
            modifier = Modifier.padding(
                start = GetPadding().xxxMediumPadding,
                end = GetPadding().xxxMediumPadding,
                top = GetPadding().mediumPadding
            ),
            backgroundColor = colorResource(id = GetColor(location = location).detail_info_card)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        start = GetPadding().xLargePadding,
                        bottom = GetPadding().xSmallPadding,
                        top = GetPadding().smallPadding,
                        end = GetPadding().smallPadding
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier
                        .padding(end = GetPadding().xSmallPadding)
                        .size(dimensionResource(id = R.dimen.icon_size)),
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