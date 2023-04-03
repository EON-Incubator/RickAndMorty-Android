package com.example.rickandmorty.ui.screens.commonUtils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickAndMortyTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState()),
    invisible: Boolean = true,
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
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }

        )
    } else {
        TopAppBar(
            //            elevation = 0.dp,
            //            backgroundColor = MaterialTheme.colors.primary,
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
                            contentDescription = "rick and morty image",
                            modifier = Modifier
                                .size(120.dp)
                            //                            .weight(1f)
                        )
                    } else {
                        Row(modifier = Modifier.fillMaxWidth().height(120.dp)) {
                        }
                    }
                    // TopBar(title = title)
                }
//                Text(text = "Rick And Morty")
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
        style = if (title.equals("Rick And Morty")) MaterialTheme.typography.h1 else MaterialTheme.typography.h2,
        color = MaterialTheme.colors.onPrimary
    )
}