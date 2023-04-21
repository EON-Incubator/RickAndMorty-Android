package com.example.rickandmorty.ui.screens.commonUtils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import com.example.rickandmorty.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickAndMortyTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    ),
    invisible: Boolean = true,
    backgroundColor: Color = MaterialTheme.colors.background,
    videoButton: Boolean = false,
    onVideoClick: (Boolean) -> Unit = {},

) {
    if (canNavigateBack) {
        TopAppBar(
            backgroundColor = backgroundColor,
            elevation = GetElevation().no,
            title = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = GetPadding().xLargePadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TopBar(title = title, modifier = Modifier.weight(1f))
                        if (videoButton) {
                            IconButton(onClick = {
                                onVideoClick(true)
                            }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_subscriptions_24),
                                    contentDescription = stringResource(R.string.play_video)
                                )
                            }
                        }
                        Demo_DropDownMenu(LocalUriHandler.current)
                    }
                }
            },
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier.weight(1f),
                            imageVector = ImageVector.vectorResource(id = R.drawable.left_arrow),
                            contentDescription = stringResource(R.string.back_button)
                        )
                        Text(
                            modifier = Modifier
                                .weight(2f)
                                .padding(bottom = GetPadding().xxSmallPadding),
                            text = stringResource(
                                R.string.back
                            )
                        )
                    }
                }
            }

        )
    } else {
        TopAppBar(
            modifier = Modifier.background(Color.Yellow),
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = backgroundColor),
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(GetPadding().xSmallPadding),

                    horizontalArrangement = Arrangement.Start
                ) {
                    if (!invisible) {
                        Image(
                            painter = painterResource(id = R.drawable.rick_and_morty),
                            contentDescription = stringResource(R.string.rick_morty_image),
                            modifier = Modifier
                                .size(dimensionResource(id = R.dimen.app_title_image_size))
                        )
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dimensionResource(id = R.dimen.app_title_image_size))
                        ) {
                        }
                    }
                }
            },
            scrollBehavior = scrollBehavior

        )
    }
}

@Composable
fun TopBar(title: String, modifier: Modifier = Modifier) {
    Text(
        textAlign = TextAlign.Center,
        modifier = modifier,
        maxLines = 1,
        text = title,
        style = if (title.equals(stringResource(R.string.rick_and_morty))) MaterialTheme.typography.h1 else MaterialTheme.typography.h2,
        color = MaterialTheme.colors.onPrimary
    )
}

@Composable
fun Demo_DropDownMenu(current: UriHandler) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = stringResource(R.string.more)
            )
        }

        androidx.compose.material3.DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.amazon)) },
                onClick = { current.openUri("https://www.amazon.com/gp/video/detail/0JHRH3V853S6L8MJQA4Q04BREF/ref=atv_dl_rdr?autoplay=1") }
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.hulu)) },
                onClick = { current.openUri("https://www.hulu.com/series/rick-and-morty-d76d6361-3fbf-4842-8dd7-e05520557280") }
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.adult_swin)) },
                onClick = { current.openUri("https://www.adultswim.com/videos/rick-and-morty") }
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.apple)) },
                onClick = { current.openUri("https://tv.apple.com/ca/show/rick-and-morty/umc.cmc.12dp30hnvyq5fbm9716puu8zc") }
            )
        }
    }
}