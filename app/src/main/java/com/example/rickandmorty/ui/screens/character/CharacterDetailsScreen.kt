package com.example.rickandmorty.ui.screens.character

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.RickAndMortyTopAppBar
import com.example.rickandmorty.ui.screens.commonUtils.GetInfoInLine
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithFourImages

@Composable
fun CharacterDetails(
    modifier: Modifier = Modifier,
    state: DetailedCharacterViewModel.detailedcharacterState,
    navigateUp: () -> Unit,
    onEpisodeClick: (String) -> Unit,
    onOriginClick: (String) -> Unit,
    onLastSeenClick: (String) -> Unit,
) {
    Log.v("id", "$state")
    if (state.isLoading) {
        Scaffold(topBar = {
            RickAndMortyTopAppBar(
                title = "loading",
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        }) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .semantics { contentDescription = "Fetching Character" }
            )
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
                onLastSeenClick = onLastSeenClick
            )
        }
    }
}

object CharacterDetailsDestination : NavigationDestination {
    override val route = "character_detail"
    override val screenTitleRes = R.string.character_detail_screen_title
}

@Composable
fun DetailedScreen(
    modifier: Modifier = Modifier,
    onEpisodeClick: (String) -> Unit,
    charInfo: DetailedCharacter?,
    onOriginClick: (String) -> Unit,
    onLastSeenClick: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn() {
            item {
                Column(
                    modifier = modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "APPEARANCE",
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp),
                        textAlign = TextAlign.Start
                    )
                    AsyncImage(
                        model = charInfo?.image.toString(),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(140.dp)
                            )
                            .size(280.dp),
                        alignment = Alignment.Center
                    )
                    Text(
                        text = "INFO",
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, bottom = 12.dp),
                        textAlign = TextAlign.Start
                    )
                    Divider(color = Color.Black, thickness = 2.dp)
                    Column {
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
                        Text(
                            text = "LOCATION",
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(start = 12.dp, top = 12.dp),
                            textAlign = TextAlign.Start
                        )
                        Divider(thickness = 2.dp)

                        Log.v("origin1", charInfo?.originId.toString())
                        GetInfoInLine(
                            icons = ImageVector
                                .vectorResource(R.drawable.trip_origin_fill0_wght400_grad0_opsz48),
                            topic = "Origin",
                            topicAnswer = charInfo?.origin.toString(),
//                            action = {
//                                onOriginClick(charInfo?.originId.toString())
//                            },
                            showIt = charInfo?.originId,
                            modifier = modifier.clickable {
                                if (charInfo?.originId != "null") {
                                    onOriginClick(charInfo?.originId.toString())
                                }
                            },
                            iconArrow = if (charInfo?.originId != "null") Icons.Outlined.KeyboardArrowRight else null

                        )
                        GetInfoInLine(
                            icons = ImageVector
                                .vectorResource(R.drawable.explore_fill0_wght400_grad0_opsz48),
                            topic = "Last Seen",
                            topicAnswer = charInfo?.lastseen.toString(),
//                            action = {
//                                onOriginClick(charInfo?.originId.toString()
//                            },
                            showIt = charInfo?.lastseenId,
                            modifier = modifier.clickable {
                                if (charInfo?.lastseenId != "null") {
                                    onOriginClick(charInfo?.lastseenId.toString())
                                }
                            },
                            iconArrow = if (charInfo?.lastseenId != "null") Icons.Outlined.KeyboardArrowRight else null
                        )
                        Text(
                            text = "EPISODES",
                            style = MaterialTheme.typography.body2
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
    }
}