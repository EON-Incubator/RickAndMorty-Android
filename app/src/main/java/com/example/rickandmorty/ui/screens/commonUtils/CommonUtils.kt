package com.example.rickandmorty.ui.screens.commonUtils

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = onFilterClick
            ) {
                Icon(
                    modifier = Modifier.size(27.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.sort),
                    contentDescription = "Filter"
                )
            }
        }
    }

    Divider(
        modifier = Modifier.height(1.dp),
        color = MaterialTheme.colors.onBackground
    )
}

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
    modifier: Modifier = Modifier.clickable {
        if (showIt != "null") {
            action()
        }
    },
    showIt: String? = null,
    action: () -> Unit = {},

) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, bottom = 5.dp, top = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Log.v(topic + 2, showIt.toString())
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
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.weight(1f),
            text = topicAnswer,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Normal
        )

        if (showIt != "null") {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = "to go on next location screen"
            )
        }
    }
    Divider(
        Modifier.height(1.dp),
        color = MaterialTheme.colors.onBackground
    )
}

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
) {
    var mutableImageLink = imageUrlLink!!.toMutableList()

    if (mutableImageLink.size < 4) {
        for (i in mutableImageLink.size..3) {
            mutableImageLink.add("")
        }
    }

    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 7.dp,
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                onClickable(id)
            }

    ) {
        Row(
            modifier = Modifier.padding(3.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.weight(1f)) {
                GetImages(mutableImageLink)
            }

            Row(modifier = Modifier.weight(2f)) {
                GetData(
                    titleName,
                    property1,
                    property2
                )
            }
        }
    }
}

/**
 * Composable function that draws row with an image
 * and some data
 * Eg: Character Row that shows in Search Screen
 **/
@Composable
fun GetRowWithOneImage(
    imageUrlLink: String,
    titleName: String,
    property1: String,
    property2: String,
    status: String,
    id: String,
    onClickable: (String) -> Unit,

) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 7.dp,
        modifier = Modifier
            .padding(5.dp)
            .semantics { contentDescription = "Single Image Row" }
            .clickable {
                onClickable(id)
            }

    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (status != "") {
                Text(
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(80.dp)
                        .rotate(-90f)
                        .semantics { contentDescription = "Item Name" }
                        .background(
                            when (status) {
                                "Dead" -> Color.Red
                                "Alive" -> Color.Green
                                else -> Color.Gray
                            }

                        ),
                    softWrap = false,
                    text = status
                )
            }

            Row(modifier = Modifier.weight(1f)) {
                AsyncImage(
                    modifier = Modifier
                        .padding(start = 15.dp, end = 7.dp, bottom = 7.dp, top = 7.dp)
                        .clip(CircleShape)
                        .size(70.dp)
                        .border(
                            BorderStroke(1.dp, color = MaterialTheme.colors.onBackground),
                            shape = CircleShape
                        ),

                    alignment = Alignment.Center,
                    model = imageUrlLink,
                    error = painterResource(R.drawable.person_image),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = "Icon of Location Characters"
                )
            }

            Row(modifier = Modifier.weight(2f)) {
                GetData(
                    titleName,
                    property1,
                    property2
                )
            }
        }
    }
}

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
                    .clip(RoundedCornerShape(5.dp)),
                model = imageUrlLink[0],
                error = painterResource(R.drawable.person_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = "Icon of Location Characters"
            )

            AsyncImage(
                modifier = Modifier
                    .padding(2.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(5.dp)),
                error = painterResource(R.drawable.person_image),
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
                    .clip(RoundedCornerShape(5.dp)),
                error = painterResource(R.drawable.person_image),
                placeholder = painterResource(R.drawable.loading_img),
                model = imageUrlLink[2],
                contentDescription = "Icon of Location Characters"
            )

            AsyncImage(
                modifier = Modifier
                    .padding(2.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(5.dp)),
                error = painterResource(R.drawable.person_image),
                placeholder = painterResource(R.drawable.loading_img),
                model = imageUrlLink[3],
                contentDescription = "Icon of Location Characters"
            )
        }
    }
}

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
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
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
        Row() {
            Text(
                text = property1,
                modifier = Modifier
                    .padding(end = 15.dp)
                    .background(Color.LightGray),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground
            )

            Text(
                modifier = Modifier
                    .background(Color.LightGray),
                text = property2,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}