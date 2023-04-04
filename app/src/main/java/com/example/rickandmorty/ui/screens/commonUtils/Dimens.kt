package com.example.rickandmorty.ui.screens.commonUtils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.example.rickandmorty.R

@Composable
fun GetDimensions(): Dimens {
    return Dimens(
        xxxSmallPadding = dimensionResource(id = R.dimen.padding_xxxsmall),
        xxSmallPadding = dimensionResource(id = R.dimen.padding_xxsmall),
        xSmallPadding = dimensionResource(id = R.dimen.padding_xsmall),
        smallPadding = dimensionResource(id = R.dimen.padding_small),

        mediumPadding = dimensionResource(id = R.dimen.padding_medium),
        xMediumPadding = dimensionResource(id = R.dimen.padding_xmedium),
        xxMediumPadding = dimensionResource(id = R.dimen.padding_xxmedium),
        xxxMediumPadding = dimensionResource(id = R.dimen.padding_xxxmedium),

        largePadding = dimensionResource(id = R.dimen.padding_large),
        xLargePadding = dimensionResource(id = R.dimen.padding_xlarge),
        xxLargePadding = dimensionResource(id = R.dimen.padding_xxlarge),
        xxxLargePadding = dimensionResource(id = R.dimen.padding_xxxlarge)

    )
}

data class Dimens(

    // padding
    // small
    val xxxSmallPadding: Dp,
    val xxSmallPadding: Dp,
    val xSmallPadding: Dp,
    val smallPadding: Dp,

    // medium
    val mediumPadding: Dp,
    val xMediumPadding: Dp,
    val xxMediumPadding: Dp,
    val xxxMediumPadding: Dp,

    // large
    val largePadding: Dp,
    val xLargePadding: Dp,
    val xxLargePadding: Dp,
    val xxxLargePadding: Dp,

)