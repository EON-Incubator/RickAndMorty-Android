package com.example.rickandmorty.ui.screens.episode

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.*
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.ScreenType
import com.example.rickandmorty.ui.screens.commonUtils.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalPagerApi::class
)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EpisodeDetails(
    state: EpisodeDetailViewModel.DetailEpisodesState,
    navigateUp: () -> Unit,
    onCharacterClick: (String) -> Unit,
    deviceType: ScreenType = ScreenType.PORTRAIT_PHONE,
) {
    var videoClicked = rememberSaveable { mutableStateOf(false) }
    Scaffold(topBar = {
        if (state.isLoading) {
            RickAndMortyTopAppBar(
                title = stringResource(R.string.loading),
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        } else {
            RickAndMortyTopAppBar(
                title = state.selectedEpisode?.name.toString(),
                canNavigateBack = true,
                navigateUp = navigateUp,
                backgroundColor = colorResource(id = R.color.episodeDetail_background),
                videoButton = true,
                onVideoClick = {
                    videoClicked.value = true
                }
            )
        }
    }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .blur(
                    when (videoClicked.value) {
                        true -> 5.dp
                        else -> 0.dp
                    }
                ),
            color = colorResource(id = R.color.episodeDetail_background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag(stringResource(id = R.string.ep_detail))
            ) {
                if (state.isLoading) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .semantics {
                                contentDescription = R.string.episode_detail_load.toString()
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column {
                            Spacer(modifier = Modifier.height(GetPadding().xxxMediumPadding))
                            Text(
                                text = stringResource(R.string.info),
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .padding(start = GetPadding().mediumPadding)
                            )

                            Spacer(modifier = Modifier.height(GetPadding().smallPadding))

                            GetInfoInLine(
                                icons = ImageVector.vectorResource(id = R.drawable.tvepisodedetail),
                                topic = stringResource(id = R.string.episode),
                                topicAnswer = stringResource(R.string.loading)
                            )

                            Row {
                                GetInfoInLine(
                                    icons = ImageVector.vectorResource(id = R.drawable.episodeairdate),
                                    topic = stringResource(id = R.string.air_date),
                                    topicAnswer = stringResource(R.string.loading)
                                )
                            }

                            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_40)))

                            Text(
                                text = stringResource(R.string.characters),
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .padding(start = GetPadding().mediumPadding)
                            )

                            LazyColumn {
                                repeat(4) {
                                    item {
                                        GetRowWithOneImage(
                                            imageUrlLink = "",
                                            titleName = "",
                                            property1 = "",
                                            property2 = "",
                                            status = "",
                                            id = "",
                                            onClickable = {},
                                            modifier = Modifier.shimmerBackground(
                                                RoundedCornerShape(
                                                    dimensionResource(id = R.dimen.spacer_40)
                                                )
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                } else if (state.selectedEpisode != null) {
                    if (deviceType == ScreenType.PORTRAIT_PHONE) {
                        Column() {
                            LazyColumn {
                                item {
                                    val pagerState = rememberPagerState()
                                    HorizontalPager(
                                        count = 6,
                                        state = pagerState,
                                        itemSpacing = 10.dp,
                                        contentPadding = PaddingValues(horizontal = 20.dp)
                                    ) { page ->

                                        Card(
                                            modifier = Modifier
                                                .padding(

                                                    bottom = GetPadding().xSmallPadding,
                                                    top = GetPadding().xSmallPadding
                                                ),
                                            border = BorderStroke(
                                                GetThickness().xxSmall,
                                                color = MaterialTheme.colors.onBackground
                                            ),
                                            shape = RoundedCornerShape(10)

                                        ) {
                                            GetCarouselImage()
                                        }
                                    }
                                }

                                item {
                                    Spacer(modifier = Modifier.height(GetPadding().xxxMediumPadding))

                                    Text(
                                        text = stringResource(R.string.description_caps),
                                        fontSize = 12.sp,
                                        modifier = Modifier
                                            .padding(start = GetPadding().mediumPadding)
                                    )

                                    Spacer(modifier = Modifier.height(GetPadding().smallPadding))

                                    Text(
                                        text = stringResource(R.string.full_desc),
                                        fontSize = 11.sp,
                                        modifier = Modifier
                                            .padding(
                                                start = GetPadding().xxMediumPadding,
                                                end = GetPadding().xxMediumPadding
                                            )
                                    )
                                }
                                item {
                                    Spacer(modifier = Modifier.height(GetPadding().smallPadding))

                                    Text(
                                        text = stringResource(R.string.info),
                                        fontSize = 12.sp,
                                        modifier = Modifier
                                            .padding(start = GetPadding().mediumPadding)
                                            .semantics {
                                                contentDescription =
                                                    R.string.detail_ep.toString()
                                            }
                                    )

                                    Spacer(modifier = Modifier.height(GetPadding().smallPadding))

                                    GetInfoInLine(
                                        icons = ImageVector.vectorResource(id = R.drawable.tvepisodedetail),
                                        topic = stringResource(id = R.string.episode),
                                        topicAnswer = state.selectedEpisode?.episode.toString()
                                    )

                                    Row {
                                        GetInfoInLine(
                                            icons = ImageVector.vectorResource(id = R.drawable.episodeairdate),
                                            topic = stringResource(id = R.string.air_date),
                                            topicAnswer = state.selectedEpisode?.air_date.toString()
                                        )
                                    }

                                    GetInfoInLine(
                                        icons = ImageVector.vectorResource(id = R.drawable.tvepisodedetail),
                                        topic = stringResource(R.string.rating),
                                        topicAnswer = state.selectedEpisode?.episode.toString()
                                    )

                                    Spacer(modifier = Modifier.height(GetPadding().xMediumPadding))
                                }

                                item {
                                    Text(
                                        text = stringResource(R.string.characters),
                                        fontSize = 12.sp,
                                        modifier = Modifier
                                            .padding(start = GetPadding().mediumPadding)
                                    )
                                }
                                if (state.selectedEpisode.characters.isNotEmpty()) {
                                    items(state.selectedEpisode.characters) { episode ->
                                        GetRowWithOneImage(
                                            imageUrlLink = episode.image.toString(),
                                            titleName = episode.name.toString(),
                                            property1 = episode.gender.toString(),
                                            property2 = episode.species.toString(),
                                            status = episode.status.toString(),
                                            id = episode.ID.toString(),
                                            onClickable = {
                                                onCharacterClick(it)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                        if (videoClicked.value) {
                            playVideo(
                                modifier = Modifier.fillMaxWidth(),
                                videoClicked = {
                                    videoClicked.value = it
                                }
                            )
                        }
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                        ) {
                            Column(modifier = Modifier.weight(2F)) {
                                Text(
                                    text = stringResource(R.string.info),
                                    fontSize = 12.sp,
                                    modifier = Modifier
                                        .padding(start = GetPadding().mediumPadding)
                                )

                                Spacer(modifier = Modifier.height(GetPadding().xSmallPadding))

                                GetInfoInLine(
                                    icons = ImageVector.vectorResource(id = R.drawable.tvepisodedetail),
                                    topic = stringResource(id = R.string.episode),
                                    topicAnswer = state.selectedEpisode?.episode.toString()
                                )

                                Row {
                                    GetInfoInLine(
                                        icons = ImageVector.vectorResource(id = R.drawable.episodeairdate),
                                        topic = stringResource(id = R.string.air_date),
                                        topicAnswer = state.selectedEpisode?.air_date.toString()

                                    )
                                }

                                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_40)))
                            }

                            Column(modifier = Modifier.weight(5F)) {
                                Text(
                                    text = stringResource(R.string.characters),
                                    fontSize = 12.sp,
                                    modifier = Modifier
                                        .padding(start = GetPadding().mediumPadding)
                                )

                                if (state.selectedEpisode.characters.isNotEmpty()) {
                                    LazyColumn {
                                        items(state.selectedEpisode.characters) { episode ->
                                            GetRowWithOneImage(
                                                imageUrlLink = episode.image.toString(),
                                                titleName = episode.name.toString(),
                                                property1 = episode.gender.toString(),
                                                property2 = episode.species.toString(),
                                                status = episode.status.toString(),
                                                id = episode.ID.toString(),
                                                onClickable = {
                                                    onCharacterClick(it)
                                                }
                                            )
                                        }
                                    }
                                } else {
                                    ImageVector.vectorResource(id = R.drawable.ic_broken_image)
                                }
                            }
                        }
                    }
                    if (videoClicked.value) {
                        playVideo(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            videoClicked = {
                                videoClicked.value = it
                            }
                        )
                    }
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_broken_image),
                        contentDescription = stringResource(R.string.broken)
                    )
                }
            }
        }
    }
}

object EpisodeDetailsDestination : NavigationDestination {
    override val route = "episode_details"
    override val screenTitleRes = R.string.episode_detail_screen_title
}

@Composable
fun GetCarouselImage() {
    AsyncImage(
        modifier = Modifier
            .size(
                width = LocalConfiguration.current.screenWidthDp.dp - 50.dp,
                height = LocalConfiguration.current.screenHeightDp.dp / 5
            ),
        alignment = Alignment.Center,
        model = "https://www.themoviedb.org/t/p/original/uCZWm1DY6UiE35aPttox4hoRrdk.jpg",
        error = painterResource(id = getErrorImage()),
        placeholder = painterResource(R.drawable.loading_img),
        contentDescription = "Crew Members",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun YoutubeScreen(
    videoId: String,
    modifier: Modifier = Modifier,
) {
    val ctx = LocalContext.current

    AndroidView(factory = {
        var view = YouTubePlayerView(it)
        val fragment = view.addYouTubePlayerListener(
            object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            }
        )
        view
    })
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun playVideo(modifier: Modifier = Modifier, videoClicked: (Boolean) -> Unit) {
    Dialog(
        onDismissRequest = { videoClicked(false) },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "TRAILER",
                    style = MaterialTheme.typography.body1
                )
                YoutubeScreen("sywZWeI_8Cg", modifier = modifier.padding(10.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onClick = { videoClicked(false) }
                ) {
                    Text(textAlign = TextAlign.End, text = "Back")
                }
            }
        }
    }
}