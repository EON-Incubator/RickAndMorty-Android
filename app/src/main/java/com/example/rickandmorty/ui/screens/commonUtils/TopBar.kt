package com.example.rickandmorty.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R

@Composable
fun RickAndMortyTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
) {
    if (canNavigateBack) {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.primary,
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
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        )
    } else {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.primary,
            title = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TopBar(title = title)
                }
            }
        )
    }
}

@Composable
fun TopBar(title: String) {
    Text(
        maxLines = 2,
        text = title,
        style = if (title.equals("Rick And Morty")) MaterialTheme.typography.h1 else MaterialTheme.typography.h2,
        color = MaterialTheme.colors.onPrimary
    )
}