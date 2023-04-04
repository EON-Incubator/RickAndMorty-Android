package com.example.rickandmorty.ui.screens.character

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
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.commonUtils.RickAndMortyTopAppBar
import com.example.rickandmorty.ui.screens.ScreenType
import com.example.rickandmorty.ui.screens.commonUtils.GetInfoInLine
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithFourImages

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
        DetailedCharacterLoader(deviceType = deviceType)
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
            .padding(top = 8.dp),
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
                            text = stringResource(R.string.info),
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp),
                            textAlign = TextAlign.Start
                        )

                        Column {
                            infoPart1(charInfo = charInfo)
                            Spacer(modifier = Modifier.height(12.dp))

                            infoPart2(charInfo = charInfo, onOriginClick = onOriginClick)
                            Spacer(modifier = Modifier.height(22.dp))
                            Text(
                                text = stringResource(R.string.episodes_all_caps),
                                style = MaterialTheme.typography.body2,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .padding(start = 16.dp, top = 12.dp),
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
                                        text = stringResource(R.string.info),
                                        style = MaterialTheme.typography.body2,
                                        modifier = Modifier
                                            .padding(start = 16.dp, bottom = 12.dp),
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
                                text = stringResource(R.string.episodes_all_caps),
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp, start = 24.dp),
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
                        .padding(end = 13.dp)
                ) {
                    topInfo(charInfo = charInfo, deviceType = deviceType)
                    Column(modifier = Modifier.weight(3f)) {
                        Text(
                            text = stringResource(R.string.info),
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .padding(start = 16.dp, bottom = 12.dp),
                            textAlign = TextAlign.Start
                        )
                        Column {
                            infoPart1(charInfo = charInfo)
                            Divider(thickness = 2.dp)
                            infoPart2(charInfo = charInfo, onOriginClick = onOriginClick)
                            Divider(thickness = 2.dp)
                        }
                    }
                }
                Column(
                    modifier = modifier.weight(2f)
                ) {
                    LazyColumn() {
                        item {
                            Text(
                                text = stringResource(R.string.episodes_all_caps),
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 14.dp, start = 16.dp),
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
            text = stringResource(R.string.appearance),
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            textAlign = TextAlign.Start
        )
        Card(shape = RoundedCornerShape(CornerSize(100.dp))) {
            AsyncImage(
                model = charInfo?.image.toString(),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp),
                alignment = Alignment.Center
            )
        }
    } else {
        Card(shape = RoundedCornerShape(CornerSize(100.dp)), modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = charInfo?.image.toString(),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
        }
    }
}

@Composable
fun infoPart1(charInfo: DetailedCharacter?) {
    GetInfoInLine(
        icons = ImageVector
            .vectorResource(R.drawable.gender),
        topic = stringResource(R.string.gender),
        topicAnswer = charInfo?.gender.toString()
    )
    GetInfoInLine(
        icons = ImageVector
            .vectorResource(R.drawable.species),
        topic = stringResource(R.string.species),
        topicAnswer = charInfo?.species.toString()
    )
    GetInfoInLine(
        icons = ImageVector
            .vectorResource(R.drawable.status),
        topic = stringResource(R.string.status),
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
        text = stringResource(R.string.location_all_caps),
        style = MaterialTheme.typography.body2,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 16.dp, top = 24.dp, bottom = 2.dp),
        textAlign = TextAlign.Start
    )

    GetInfoInLine(
        icons = ImageVector
            .vectorResource(R.drawable.origin),
        topic = stringResource(R.string.origin),
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
            .vectorResource(R.drawable.lastseen),
        topic = stringResource(R.string.last_seen),
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