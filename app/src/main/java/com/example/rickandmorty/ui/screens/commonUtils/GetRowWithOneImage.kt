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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.style.TextAlign
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
        shape = RoundedCornerShape(GetThickness().large),
        elevation = GetElevation().medium,
        modifier = modifier
            .padding(GetPadding().xSmallPadding)
            .testTag(stringResource(id = R.string.single_image_row))
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
                            .padding(
                                start = GetPadding().xxMediumPadding,
                                end = GetPadding().smallPadding,
                                bottom = GetPadding().xSmallPadding,
                                top = GetPadding().xSmallPadding
                            )
                            .clip(CircleShape)
                            .size(dimensionResource(id = R.dimen.row_one_image_size))
                            .border(
                                BorderStroke(
                                    GetThickness().xxSmall,
                                    color = MaterialTheme.colors.onBackground
                                ),
                                shape = CircleShape
                            ),

                        alignment = Alignment.Center,
                        model = imageUrlLink,
                        error = painterResource(id = getErrorImage()),
                        placeholder = painterResource(R.drawable.loading_img),
                        contentDescription = stringResource(R.string.episodes_all_caps)
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
                    contentDescription = stringResource(R.string.go_to_next_screen)
                )
            }

            if (status != "") {
                Text(
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .offset(x = dimensionResource(id = R.dimen.ribbon_offset))
                        .padding(top = GetPadding().mediumPadding)
                        .width(dimensionResource(id = R.dimen.row_one_image_width))
                        .rotate(-38f)
                        .testTag(stringResource(id = R.string.item_name))
                        .background(
                            when (status) {
                                stringResource(R.string.dead_leading_caps) -> LessRed
                                stringResource(R.string.alive_leading_caps) -> LessGreen
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