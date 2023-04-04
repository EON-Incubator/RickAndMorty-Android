package com.example.rickandmorty.ui.screens.commonUtils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*

import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
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

) {
    if (canNavigateBack) {
        TopAppBar(
            backgroundColor = backgroundColor,
            elevation = 0.dp,
            title = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) { TopBar(title = title) }
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
                            modifier = Modifier.weight(2f).padding(bottom = 3.dp),
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
                        .padding(5.dp),

                    horizontalArrangement = Arrangement.Start

                ) {
                    if (!invisible) {
                        Image(
                            painter = painterResource(id = R.drawable.rick_and_morty),
                            contentDescription = stringResource(R.string.rick_morty_image),
                            modifier = Modifier
                                .size(120.dp)
                        )
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
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
fun TopBar(title: String) {
    Text(
        maxLines = 2,
        text = title,
        style = if (title.equals(stringResource(R.string.rick_and_morty))) MaterialTheme.typography.h1 else MaterialTheme.typography.h2,
        color = MaterialTheme.colors.onPrimary
    )
}