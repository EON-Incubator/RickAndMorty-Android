package com.example.rickandmorty.ui.screens.character

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.commonUtils.*
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Characters(
    state: CharacterViewModel.CharacterState,
    genderVal: String,
    statusVal: String,
    onClick: (id: String) -> Unit,
    onCharacterClick: (code: String) -> Unit,
    listState: LazyGridState,
    selectGender: () -> Unit,
    changeGender: (String) -> Unit,
    changeStatus: (String) -> Unit,
    onRefresh: () -> Unit = {},
    isRefreshing: Boolean = false,

) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    var showFilter by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            RickAndMortyTopAppBar(
                title = "Rick And Morty",
                canNavigateBack = false,
                navigateUp = {},
                scrollBehavior = scrollBehavior,
                invisible = false
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .semantics { contentDescription = "characters" }
        ) {
            if (showFilter) {
                DialogBox(
                    genderVal,
                    statusVal,
                    selectGender,
                    changeGender,
                    changeStatus,
                    modifier = Modifier.semantics { contentDescription = "filter" }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .semantics { contentDescription = "characters" }
            ) {
                ScreenNameBar(
                    name = "Characters",
                    onFilterClick = { showFilter = !showFilter },
                    putIcon = true
                )
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = onRefresh,
                    indicator = { state, refreshTrigger ->
                        SwipeRefreshIndicator(
                            state = state,
                            refreshTriggerDistance = refreshTrigger,
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = MaterialTheme.colors.onPrimary
                        )
                    }
                ) {
                    if (state.isLoading) {
                        CharacterLoader()
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(GetPadding().smallPadding),
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(GetPadding().smallPadding),
                            state = listState
                        ) {
                            items(state.characters) { character ->
                                characterItem(
                                    charstate = character,
                                    onClick = onClick

                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

object CharacterDestination : NavigationDestination {
    override val route = "characters"
    override val screenTitleRes = R.string.characters_screen_title
}

@Composable
private fun characterItem(
    charstate: Character,
    onClick: (id: String) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(GetPadding().xMediumPadding)
            .fillMaxSize()
            .clip(RoundedCornerShape(GetThickness().xLarge))
            .clickable {
                onClick(charstate.ID.toString())
            },
        elevation = GetElevation().xLarge
    ) {
        Box(contentAlignment = Alignment.BottomCenter) {
            AsyncImage(
                model = charstate.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .size(dimensionResource(id = R.dimen.character_image_size))
                    .clip(
                        RoundedCornerShape(GetThickness().medium)
                    ),
                contentScale = ContentScale.Crop
            )
            Text(
                text = charstate.name.toString(),
                modifier = Modifier
                    .background(MaterialTheme.colors.primaryVariant)
                    .fillMaxWidth()
                    .semantics { contentDescription = "Row" },
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body1
            )
        }
    }
}