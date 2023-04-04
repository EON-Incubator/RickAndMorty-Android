package com.example.rickandmorty.ui.screens.commonUtils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.example.rickandmorty.R

@Composable
fun GetPadding(): Padding {
    return Padding(
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

@Composable
fun GetElevation(): Depth{
    return Depth(
        no = dimensionResource(id = R.dimen.elevation_no),
        xxSmall = dimensionResource(id = R.dimen.elevation_xxsmall),
        xSmall = dimensionResource(id = R.dimen.elevation_xsmall),
        small = dimensionResource(id = R.dimen.elevation_small),
        xMedium = dimensionResource(id = R.dimen.elevation_xmedium),
        medium = dimensionResource(id = R.dimen.elevation_medium),
        large = dimensionResource(id = R.dimen.elevation_large),
        xLarge = dimensionResource(id = R.dimen.elevation_xlarge)
    )
}

@Composable
fun GetThickness(): Depth{
    return Depth(
        no = dimensionResource(id = R.dimen.thickness_no),
        xxSmall = dimensionResource(id = R.dimen.thickness_xxsmall),
        xSmall = dimensionResource(id = R.dimen.thickness_xsmall),
        small = dimensionResource(id = R.dimen.thickness_small),
        xMedium = dimensionResource(id = R.dimen.thickness_xmedium),
        medium = dimensionResource(id = R.dimen.thickness_medium),
        large = dimensionResource(id = R.dimen.thickness_large),
        xLarge = dimensionResource(id = R.dimen.thickness_xlarge)
    )

}

data class Padding(

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

data class Depth(
    val no: Dp,
    val xxSmall: Dp,
    val xSmall: Dp,
    val small: Dp,
    val xMedium: Dp,
    val medium: Dp,
    val large: Dp,
    val xLarge: Dp,

)