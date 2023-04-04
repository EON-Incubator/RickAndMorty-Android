package com.example.rickandmorty.ui.screens.commonUtils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.theme.LessGreen
import com.example.rickandmorty.ui.theme.LessRed

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
    modifier: Modifier = Modifier,
    icons: List<ImageVector> = emptyList(),

) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 7.dp,
        modifier = modifier
            .padding(5.dp)
            .semantics { contentDescription = "Single Image Row" }
            .clickable {
                onClickable(id)
            },
        backgroundColor =
        colorResource(
            id = GetColor(location = true)
                .detail_resident_card_background
        )
    ) {
        Box() {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(modifier = Modifier.weight(1f)) {
                    AsyncImage(
                        modifier = modifier
                            .padding(start = 15.dp, end = 7.dp, bottom = 7.dp, top = 7.dp)
                            .clip(CircleShape)
                            .size(70.dp)
                            .border(
                                BorderStroke(1.dp, color = MaterialTheme.colors.onBackground),
                                shape = CircleShape
                            ),

                        alignment = Alignment.Center,
                        model = imageUrlLink,
                        error = painterResource(id = getErrorImage()),
                        placeholder = painterResource(R.drawable.loading_img),
                        contentDescription = "Icon of Location Characters"
                    )
                }

                Row(modifier = modifier.weight(3f)) {
                    GetData(
                        titleName,
                        property1,
                        property2,
                        icons
                    )
                }

                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowRight,
                    contentDescription = "Go to next screen"
                )
            }

            if (status != "") {
                Text(
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .offset(x = -30.dp)
                        .padding(top = GetDimensions().mediumPadding)
                        .width(100.dp)
                        .rotate(-38f)
                        .semantics { contentDescription = "Item Name" }
                        .background(
                            when (status) {
                                "Dead" -> LessRed
                                "Alive" -> LessGreen
                                else -> Color.Gray
                            }
                        ),
                    softWrap = false,
                    text = status,
                    fontSize = 10.sp
                )
            }
        }
    }
}