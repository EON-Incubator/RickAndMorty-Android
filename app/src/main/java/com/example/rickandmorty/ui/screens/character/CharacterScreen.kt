package com.example.rickandmorty.ui.screens.character

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.commonUtils.RickAndMortyTopAppBar
import com.example.rickandmorty.ui.screens.commonUtils.ScreenNameBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
// @ExperimentalMaterialApi
@ExperimentalMaterial3Api
// @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun Characters(
    state: CharacterViewModel.CharacterState,
    genderVal: String,
    statusVal: String,
    onClick: (id: String) -> Unit,
    listState: LazyGridState,
    changeGender: (String) -> Unit,
    changeStatus: (String) -> Unit,
    onRefresh: () -> Unit = {},
    isRefreshing: Boolean = false,
    applyFilter: () -> Unit,

) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    val stateB = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        animationSpec = tween(
            durationMillis = 700,
            easing = FastOutSlowInEasing
        )
    )
    val scope = rememberCoroutineScope()

    var showFilter by remember {
        mutableStateOf(false)
    }

//            var showFilter by remember {
//                mutableStateOf(false)
//            }

    Scaffold(
        topBar = {
            RickAndMortyTopAppBar(
                title = stringResource(id = R.string.rick_and_morty),
                canNavigateBack = false,
                navigateUp = {},
                scrollBehavior = scrollBehavior,
                invisible = false
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .semantics { contentDescription = "characters" }.padding(it)
        ) {
            ModalBottomSheetLayout(
                sheetContent = {
                    FilterData(
                        genderVal = genderVal,
                        statusVal = statusVal,
                        applyFilter = applyFilter,
                        changeGender = changeGender,
                        changeStatus = changeStatus,
                        close = stateB
                    )
                },
                sheetState = stateB,
                sheetShape = RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics { contentDescription = "characters" }

                ) {
                    ScreenNameBar(
                        name = "Characters",

                        onFilterClick = {
                            scope.launch { stateB.show() }
                        },

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
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(8.dp),
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
            .padding(12.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onClick(charstate.ID.toString())
            },
        elevation = 12.dp
    ) {
        Box(contentAlignment = Alignment.BottomCenter) {
            AsyncImage(
                model = charstate.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .size(150.dp)
                    .clip(
                        RoundedCornerShape(8.dp)
                    ),
                contentScale = ContentScale.Crop
            )
            Text(
                text = charstate.name.toString(),
                modifier = Modifier
                    .background(MaterialTheme.colors.primaryVariant)
                    .fillMaxWidth()
                    .semantics { contentDescription = R.string.row.toString() },
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body1
            )
        }
    }
}