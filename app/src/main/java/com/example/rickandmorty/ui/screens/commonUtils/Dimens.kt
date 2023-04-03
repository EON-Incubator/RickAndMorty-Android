package com.example.rickandmorty.ui.screens.commonUtils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.example.rickandmorty.R

@Composable
fun GetDimensions(): Dimens {
    return Dimens(
        xSmallPadding = dimensionResource(id = R.dimen.padding_xsmall),
        smallPadding = dimensionResource(id = R.dimen.padding_small),
        mediumPadding = dimensionResource(id = R.dimen.padding_medium),
        largePadding = dimensionResource(id = R.dimen.padding_large),
        xLargePadding = dimensionResource(id = R.dimen.padding_xlarge)

    )
}

data class Dimens(

    // padding
    val xSmallPadding: Dp,
    val smallPadding: Dp,
    val mediumPadding: Dp,
    val largePadding: Dp,
    val xLargePadding: Dp,

)