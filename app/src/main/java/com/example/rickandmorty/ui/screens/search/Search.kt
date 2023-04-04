package com.example.rickandmorty.ui.screens.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.ScreenType
import com.example.rickandmorty.ui.screens.commonUtils.GetPadding
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithFourImages
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithOneImage

@Composable
fun Search(
    searchResultState: SearchViewModel.SearchResult,
    onValueChange: (name: String) -> Unit,
    query: State<TextFieldValue>,
    onLocationClick: (id: String) -> Unit,
    onCharacterClick: (id: String) -> Unit,
    onShowCharacters: () -> Unit,
    showCharacters: Boolean,
    onShowLocations: () -> Unit,
    showLocations: Boolean,
    updateCharacterList: () -> Unit,
    updateLocationList: () -> Unit,
    searchListState: LazyListState,
    deviceType: ScreenType,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if (deviceType == ScreenType.PORTRAIT_PHONE) {
            SearchBar(
                query = query,
                showLocations = showLocations,
                onValueChange = onValueChange,
                onShowLocations = onShowLocations,
                showCharacters = showCharacters,
                onShowCharacters = onShowCharacters
            )
        }
        LazyColumn(
            state = searchListState,
            modifier = Modifier.testTag("search_lazy_column")
        ) {
            if (deviceType != ScreenType.PORTRAIT_PHONE) {
                item {
                    SearchBar(
                        query = query,
                        showLocations = showLocations,
                        onValueChange = onValueChange,
                        onShowLocations = onShowLocations,
                        showCharacters = showCharacters,
                        onShowCharacters = onShowCharacters

                    )
                }
            }
            if (searchResultState.isLoading) {
                item {
                    SearchLoader()
                }
            } else {
                if (showCharacters) {
                    if (searchResultState.characterData?.characters != null) {
                        item {
                            Spacer(modifier = Modifier.height(GetPadding().xSmallPadding))
                            Text(
                                text = "characters",
                                Modifier
                                    .background(Color.LightGray)
                                    .fillMaxWidth()
                                    .padding(GetPadding().xxxSmallPadding)
                            )
                        }
                        Log.v(
                            "Search test: Screen:",
                            searchResultState.characterData.characters.toString()
                        )
                        if (searchResultState.characterData.characters.isEmpty()) {
                            item {
                                Spacer(modifier = Modifier.height(GetPadding().xSmallPadding))
                                Text(
                                    text = "No characters found matching the input: ${query.value.text}",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(GetPadding().xxxSmallPadding)
                                )
                            }
                        } else {
                            items(
                                searchResultState.characterData.characters,
                                key = { it.ID.toString() }
                            ) { item ->
                                GetRowWithOneImage(
                                    imageUrlLink = item.image.toString(),
                                    titleName = item.name.toString(),
                                    property1 = item.species.toString(),
                                    property2 = item.gender.toString(),
                                    status = item.status.toString(),
                                    id = item.ID.toString(),
                                    onClickable = onCharacterClick
                                )
                            }
                        }

                        if (searchResultState.characterData.pages?.next != null) {
                            item {
                                Button(
                                    onClick = updateCharacterList,
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(GetPadding().mediumPadding)
                                ) {
                                    Text(
                                        text =
                                        "Load More",
                                        modifier = Modifier.semantics {
                                            contentDescription = "Load More Characters"
                                        }
                                    )
                                }
                            }
                        }
                    } else {
                        item {
                            Spacer(modifier = Modifier.height(GetPadding().xSmallPadding))
                            Text(
                                text = "Characters",
                                Modifier
                                    .background(Color.LightGray)
                                    .fillMaxWidth()
                                    .padding(GetPadding().xxxSmallPadding)
                            )
                            Spacer(modifier = Modifier.height(GetPadding().xSmallPadding))
                            Text(
                                text = "Type to search character by name",
                                Modifier
                                    .fillMaxWidth()
                                    .padding(GetPadding().xxxSmallPadding)
                            )
                        }
                    }
                }
                if (showLocations) {
                    if (searchResultState.locationByName?.locations != null) {
                        item {
                            Spacer(modifier = Modifier.height(GetPadding().xSmallPadding))
                            Text(
                                text = "Locations",
                                Modifier
                                    .background(Color.LightGray)
                                    .fillMaxWidth()
                                    .padding(GetPadding().xxxSmallPadding)
                            )
                        }
                        if (searchResultState.locationByName.locations.isEmpty()) {
                            item {
                                Spacer(modifier = Modifier.height(GetPadding().xSmallPadding))
                                Text(
                                    text = "No locations found matching the input: ${query.value.text}",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(GetPadding().xxxSmallPadding)
                                )
                            }
                        } else {
                            items(searchResultState.locationByName.locations) { item ->
                                GetRowWithFourImages(
                                    imageUrlLink = item.images,
                                    titleName = item.name.toString(),
                                    property1 = item.type.toString(),
                                    property2 = item.dimension.toString(),
                                    id = item.id.toString(),
                                    onClickable = { onLocationClick(item.id.toString()) }
                                )
                            }
                        }
                        if (searchResultState.locationByName?.pages?.next != null || searchResultState.locationByType?.pages?.next != null) {
                            item {
                                Button(
                                    onClick = updateLocationList,
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(GetPadding().mediumPadding)
                                ) {
                                    Text(
                                        text =
                                        "Load More",
                                        modifier = Modifier.semantics {
                                            contentDescription = "Load More Locations"
                                        }
                                    )
                                }
                            }
                        }
                    } else {
                        item {
                            Spacer(modifier = Modifier.height(GetPadding().xSmallPadding))
                            Text(
                                text = "Locations",
                                Modifier
                                    .background(Color.LightGray)
                                    .fillMaxWidth()
                                    .padding(GetPadding().xxxSmallPadding)
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(GetPadding().xSmallPadding))
                            Text(
                                text = "Type to search location by name or type",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(GetPadding().xxxSmallPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    onValueChange: (name: String) -> Unit,
    query: State<TextFieldValue>,
    onShowCharacters: () -> Unit,
    showCharacters: Boolean,
    showLocations: Boolean,
    onShowLocations: () -> Unit,
) {
    Row(modifier = Modifier.padding(GetPadding().xSmallPadding)) {
        OutlinedTextField(
            value = query.value.text,
            onValueChange = onValueChange,
            Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "Search Bar" }
        )
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(GetPadding().xSmallPadding),
        modifier = Modifier.padding(GetPadding().xSmallPadding)
    ) {
        Button(
            onClick = onShowCharacters,
            Modifier
                .weight(1.0f)
                .semantics { contentDescription = "Characters" },
            colors = if (showCharacters) {
                ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
            } else {
                ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
            }
        ) {
            if (showCharacters) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = R.drawable.outline_check_box_24
                    ),
                    contentDescription = "Selected",
                    Modifier.padding(horizontal = GetPadding().xSmallPadding)
                )
            } else {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = R.drawable.outline_check_box_outline_blank_24
                    ),
                    contentDescription = "Selected",
                    Modifier.padding(horizontal = GetPadding().xSmallPadding)
                )
            }
            Text(
                text =
                "Characters"
            )
        }
        Button(
            onClick = onShowLocations,
            Modifier
                .weight(1.0f)
                .semantics { contentDescription = "Locations" },
            colors = if (showLocations) {
                ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
            } else {
                ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
            }
        ) {
            if (showLocations) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id =
                        R.drawable.outline_check_box_24
                    ),
                    contentDescription = "Selected",
                    Modifier.padding(horizontal = GetPadding().xSmallPadding)
                )
            } else {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id =
                        R.drawable.outline_check_box_outline_blank_24
                    ),
                    contentDescription = "Selected",
                    Modifier.padding(horizontal = GetPadding().xSmallPadding)
                )
            }
            Text(text = "Locations")
        }
    }
}

@Composable
fun ResultList() {
}

object SearchDestination : NavigationDestination {
    override val route = "search"
    override val screenTitleRes = R.string.search_screen_title
}