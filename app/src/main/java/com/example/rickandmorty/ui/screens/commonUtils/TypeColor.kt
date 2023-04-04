package com.example.rickandmorty.ui.screens.commonUtils

import androidx.compose.runtime.Composable
import com.example.rickandmorty.R

@Composable
fun GetColor(location: Boolean): TypeColor {
    if (location) {
        return TypeColor(
            background = R.color.location_background,
            card_background = R.color.location_card_background,
            property1 = R.color.location_property1,
            property2 = R.color.location_property2,

            detail_background = R.color.locationDetail_background,
            detail_info_card = R.color.locationDetail_info_card,
            detail_resident_card_background = R.color.locationDetail_resident_card_background,
            detail_resident_property1 = R.color.locationDetail_resident_property1,
            detail_resident_property2 = R.color.locationDetail_resident_property2
        )
    }

    return TypeColor(
        background = R.color.episode_background,
        card_background = R.color.episode_card_background,
        property1 = R.color.episode_property1,
        property2 = R.color.episode_property2,

        detail_background = R.color.episodeDetail_background,
        detail_info_card = R.color.episodeDetail_info_card,
        detail_resident_card_background = R.color.episodeDetail_resident_card_background,
        detail_resident_property1 = R.color.episodeDetail_resident_property1,
        detail_resident_property2 = R.color.episodeDetail_resident_property2
    )
}

data class TypeColor(
    val background: Int,
    val card_background: Int,
    val property1: Int,
    val property2: Int,

    val detail_background: Int,
    val detail_info_card: Int,
    val detail_resident_card_background: Int,
    val detail_resident_property1: Int,
    val detail_resident_property2: Int,
)