package com.example.rickandmorty.ui.screens.character

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.ScreenType
import com.example.rickandmorty.ui.screens.commonUtils.*

object CharacterDetailsDestination : NavigationDestination {
    override val route = "character_detail"
    override val screenTitleRes = R.string.character_detail_screen_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetails(
    modifier: Modifier = Modifier,
    state: DetailedCharacterViewModel.detailedcharacterState,
    navigateUp: () -> Unit,
    onEpisodeClick: (String) -> Unit,
    onOriginClick: (String) -> Unit,
    onLastSeenClick: (String) -> Unit,
    deviceType: ScreenType = ScreenType.PORTRAIT_PHONE,
) {
    if (state.isLoading) {
        Scaffold(topBar = {
            RickAndMortyTopAppBar(
                title = "loading",
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        }) {
            DetailedCharacterLoader(modifier = Modifier.padding(it))
        }
    } else {
        Scaffold(topBar = {
            RickAndMortyTopAppBar(
                title = state.character?.name.toString(),
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        }) {
            DetailedScreen(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it),
                charInfo = state.character,
                onEpisodeClick = onEpisodeClick,
                onOriginClick = onOriginClick,
                onLastSeenClick = onLastSeenClick,
                deviceType = deviceType
            )
        }
    }
}

@Composable
fun DetailedScreen(
    modifier: Modifier = Modifier,
    onEpisodeClick: (String) -> Unit,
    charInfo: DetailedCharacter?,
    onOriginClick: (String) -> Unit,
    onLastSeenClick: (String) -> Unit,
    deviceType: ScreenType = ScreenType.PORTRAIT_PHONE,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = GetPadding().smallPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (deviceType == ScreenType.PORTRAIT_PHONE) {
            LazyColumn() {
                item {
                    Column(
                        modifier = modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        topInfo(charInfo = charInfo, deviceType = deviceType)
                        Text(
                            text = "INFO",
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = GetPadding().xxxMediumPadding),
                            textAlign = TextAlign.Start
                        )

                        Column {
                            infoPart1(charInfo = charInfo)
                            Spacer(modifier = Modifier.height(GetPadding().xMediumPadding))

                            infoPart2(charInfo = charInfo, onOriginClick = onOriginClick)
                            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.character_info_space_text)))
                            Text(
                                text = "EPISODES",
                                style = MaterialTheme.typography.body2,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(dimensionResource(id = R.dimen.character_text_height))
                                    .padding(
                                        start = GetPadding().xxxMediumPadding,
                                        top = GetPadding().xMediumPadding
                                    ),
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
                charInfo?.let {
                    items(it.episode) { eachEpisode ->

                        GetRowWithFourImages(
                            imageUrlLink = eachEpisode.images,
                            titleName = eachEpisode.name.toString(),
                            property1 = eachEpisode.episode.toString(),
                            property2 = eachEpisode.air_date.toString(),
                            onClickable = {
                                onEpisodeClick(eachEpisode.id.toString())
                            },
                            id = eachEpisode.id.toString()
                        )
                    }
                }
            }
        } else if (deviceType == ScreenType.LANDSCAPE_PHONE) {
            Row() {
                Column(Modifier.weight(1f)) {
                    topInfo(charInfo = charInfo, deviceType = deviceType)
                }

                Row(Modifier.weight(3f)) {
                    LazyColumn() {
                        item {
                            Row(
                                modifier = modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(2f)) {
                                    Text(
                                        text = "INFO",
                                        style = MaterialTheme.typography.body2,
                                        modifier = Modifier
                                            .padding(start = GetPadding().xxxMediumPadding, bottom = GetPadding().xMediumPadding),
                                        textAlign = TextAlign.Start
                                    )
                                    Column {
                                        infoPart1(charInfo = charInfo)

                                        infoPart2(
                                            charInfo = charInfo,
                                            onOriginClick = onOriginClick
                                        )
                                    }
                                }
                            }
                        }
                        item {
                            Text(
                                text = "EPISODES",
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = GetPadding().xxxMediumPadding,
                                        start = GetPadding().xxxLargePadding
                                    ),
                                textAlign = TextAlign.Center
                            )
                        }

                        charInfo?.let {
                            items(it.episode) { eachEpisode ->

                                GetRowWithFourImages(
                                    imageUrlLink = eachEpisode.images,
                                    titleName = eachEpisode.name.toString(),
                                    property1 = eachEpisode.episode.toString(),
                                    property2 = eachEpisode.air_date.toString(),
                                    onClickable = {
                                        onEpisodeClick(eachEpisode.id.toString())
                                    },
                                    id = eachEpisode.id.toString()
                                )
                            }
                        }
                    }
                }
            }
        } else {
            Row() {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = GetPadding().xxMediumPadding)
                ) {
                    topInfo(charInfo = charInfo, deviceType = deviceType)
                    Column(modifier = Modifier.weight(3f)) {
                        Text(
                            text = "INFO",
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .padding(start = GetPadding().xxxMediumPadding, bottom = GetPadding().xMediumPadding),
                            textAlign = TextAlign.Start
                        )
                        Column {
                            infoPart1(charInfo = charInfo)
                            Divider(thickness = GetThickness().xSmall)
                            infoPart2(charInfo = charInfo, onOriginClick = onOriginClick)
                            Divider(thickness = GetThickness().xSmall)
                        }
                    }
                }
                Column(
                    modifier = modifier.weight(2f)
                ) {
                    LazyColumn() {
                        item {
                            Text(
                                text = "EPISODES",
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = GetPadding().xxMediumPadding,
                                        start = GetPadding().xxxMediumPadding
                                    ),
                                textAlign = TextAlign.Center
                            )
                        }
                        charInfo?.let {
                            items(it.episode) { eachEpisode ->
                                GetRowWithFourImages(
                                    imageUrlLink = eachEpisode.images,
                                    titleName = eachEpisode.name.toString(),
                                    property1 = eachEpisode.episode.toString(),
                                    property2 = eachEpisode.air_date.toString(),
                                    onClickable = {
                                        onEpisodeClick(eachEpisode.id.toString())
                                    },
                                    id = eachEpisode.id.toString()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun topInfo(charInfo: DetailedCharacter?, deviceType: ScreenType) {
    if (deviceType == ScreenType.PORTRAIT_PHONE) {
        Text(
            text = "APPEARANCE",
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = GetPadding().xxxMediumPadding),
            textAlign = TextAlign.Start
        )
        Card(shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.character_card_corner)))) {
            AsyncImage(
                model = charInfo?.image.toString(),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.character_card_image_size)),
                alignment = Alignment.Center
            )
        }
    } else {
        Card(shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.character_card_corner))), border = BorderStroke(GetThickness().xLarge, Color.Black)) {
            AsyncImage(
                model = charInfo?.image.toString(),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.character_image_size)),
                alignment = Alignment.Center
            )
        }
    }
}

@Composable
fun infoPart1(charInfo: DetailedCharacter?) {
    GetInfoInLine(
        icons = ImageVector
            .vectorResource(R.drawable.man_fill0_wght400_grad0_opsz48),
        topic = "Gender",
        topicAnswer = charInfo?.gender.toString()
    )
    GetInfoInLine(
        icons = ImageVector
            .vectorResource(R.drawable.category_fill0_wght400_grad0_opsz48),
        topic = "Species",
        topicAnswer = charInfo?.species.toString()
    )
    GetInfoInLine(
        icons = ImageVector
            .vectorResource(R.drawable.deceased_fill0_wght400_grad0_opsz48),
        topic = "Status",
        topicAnswer = charInfo?.status.toString()
    )
}

@Composable
fun infoPart2(
    charInfo: DetailedCharacter?,
    onOriginClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "LOCATION",
        style = MaterialTheme.typography.body2,
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.character_text_height))
            .padding(
                start = GetPadding().xxxMediumPadding,
                top = GetPadding().xxxLargePadding,
                bottom = GetPadding().xxxSmallPadding
            ),
        textAlign = TextAlign.Start
    )

    GetInfoInLine(
        icons = ImageVector
            .vectorResource(R.drawable.trip_origin_fill0_wght400_grad0_opsz48),
        topic = "Origin",
        topicAnswer = charInfo?.origin.toString(),
        showIt = charInfo?.originId,
        modifier = modifier.clickable {
            if (charInfo?.originId != "null") {
                onOriginClick(charInfo?.originId.toString())
            }
        },
        iconArrow = if (charInfo?.originId != "null") {
            Icons.Outlined.KeyboardArrowRight
        } else {
            null
        }
    )
    GetInfoInLine(
        icons = ImageVector
            .vectorResource(R.drawable.explore_fill0_wght400_grad0_opsz48),
        topic = "Last Seen",
        topicAnswer = charInfo?.lastseen.toString(),
        showIt = charInfo?.lastseenId,
        modifier = modifier.clickable {
            if (charInfo?.lastseenId != "null") {
                onOriginClick(charInfo?.lastseenId.toString())
            }
        },
        iconArrow = if (charInfo?.lastseenId != "null") {
            Icons.Outlined.KeyboardArrowRight
        } else {
            null
        }
    )
}