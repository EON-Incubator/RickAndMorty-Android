package com.example.rickandmorty.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R

@ExperimentalMaterialApi
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
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.arrow_backward_circle),
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        )
    } else {
        TopAppBar(
            elevation = 0.dp,

            backgroundColor = MaterialTheme.colors.primary,
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),

                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.rick_and_morty),
                        contentDescription = "rick and morty image",
                        modifier = Modifier
                            .size(120.dp)
//                            .weight(1f)
                    )
                    // TopBar(title = title)
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