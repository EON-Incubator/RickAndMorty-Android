package com.example.rickandmorty.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.DataState
import com.example.rickandmorty.ui.screens.ScreenType
import com.example.rickandmorty.ui.screens.commonUtils.GetPadding
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithFourImages
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithOneImage
import com.example.rickandmorty.ui.screens.location.LocationLoaderCells

@Composable
fun Search(
    searchResultState: SearchViewModel.SearchResult,
    onValueChange: (name: String) -> Unit,
    query: State<TextFieldValue>,
    onLocationClick: (id: String) -> Unit,
    onCharacterClick: (id: String) -> Unit,
    onEpisodeClick: (id: String) -> Unit,
    onShowCharacters: () -> Unit,
    showCharacters: Boolean,
    onShowLocations: () -> Unit,
    showLocations: Boolean,
    onShowEpisodes: () -> Unit,
    showEpisodes: Boolean,
    updateCharacterList: () -> Unit,
    updateLocationList: () -> Unit,
    searchListState: LazyListState,
    deviceType: ScreenType,
    onResetQuery: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if (deviceType == ScreenType.PORTRAIT_PHONE) {
            SearchBar(
                query = query,
                showLocations = showLocations,
                onValueChange = onValueChange,
                onShowLocations = onShowLocations,
                showCharacters = showCharacters,
                onShowCharacters = onShowCharacters,
                showEpisodes = showEpisodes,
                onShowEpisodes = onShowEpisodes,
                onResetQuery = onResetQuery
            )
        }
        LazyColumn(
            state = searchListState,
            modifier = Modifier.testTag(stringResource(R.string.search_lazy_column))
        ) {
            if (deviceType != ScreenType.PORTRAIT_PHONE) {
                item {
                    SearchBar(
                        query = query,
                        showLocations = showLocations,
                        onValueChange = onValueChange,
                        onShowLocations = onShowLocations,
                        showCharacters = showCharacters,
                        onShowCharacters = onShowCharacters,
                        showEpisodes = showEpisodes,
                        onShowEpisodes = onShowEpisodes,
                        onResetQuery = onResetQuery

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
                                text = stringResource(R.string.characters_small_case),
                                Modifier
                                    .background(Color.LightGray)
                                    .fillMaxWidth()
                                    .padding(GetPadding().xxxSmallPadding)
                            )
                        }
                        if (searchResultState.characterData.characters.isEmpty()) {
                            item {
                                Spacer(modifier = Modifier.height(GetPadding().xSmallPadding))
                                Text(
                                    text = stringResource(R.string.no_characters_found) + " ${query.value.text}",
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

                        if (searchResultState.characterData.pages?.next != null && !DataState.isLocal) {
                            item {
                                Button(
                                    onClick = updateCharacterList,
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(GetPadding().mediumPadding)
                                ) {
                                    Text(
                                        text =
                                        stringResource(R.string.load_more),
                                        modifier = Modifier
                                            .semantics {
                                                /** Hardcoded string used to testing */
                                                contentDescription = "Load More Characters"
                                            }
                                    )
                                }
                            }
                            item {
                                if (searchResultState.isCharacterUpdateLoading) {
                                    LocationLoaderCells(deviceType)
                                }
                            }
                        }
                    } else {
                        item {
                            Spacer(modifier = Modifier.height(GetPadding().xSmallPadding))
                            Text(
                                text = stringResource(R.string.characters_screen_title),
                                Modifier
                                    .background(Color.LightGray)
                                    .fillMaxWidth()
                                    .padding(GetPadding().xxxSmallPadding)
                            )
                            Spacer(modifier = Modifier.height(GetPadding().xSmallPadding))
                            Text(
                                text = stringResource(R.string.type_to_search_char),
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
                                text = stringResource(R.string.locations_screen_title),
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
                                    text = stringResource(R.string.no_location_found) + "${query.value.text}",
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
                        item {
                            if (searchResultState.isLocationUpdateLoading) {
                                LocationLoaderCells(deviceType)
                            }
                        }
                        if (
                            (searchResultState.locationByName.pages?.next != null || searchResultState.locationByType?.pages?.next != null) &&
                            !DataState.isLocal
                        ) {
                            item {
                                Button(
                                    onClick = updateLocationList,
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(GetPadding().mediumPadding)
                                ) {
                                    Text(
                                        text =
                                        stringResource(id = R.string.load_more),
                                        /** Hardcoded string used to testing */
                                        modifier = Modifier
                                            .semantics {
                                                /** Hardcoded string used to testing */
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
                                text = stringResource(id = R.string.locations_screen_title),
                                Modifier
                                    .background(Color.LightGray)
                                    .fillMaxWidth()
                                    .padding(GetPadding().xxxSmallPadding)
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(GetPadding().xSmallPadding))
                            Text(
                                text = stringResource(R.string.type_search_loc_by_name_type),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(GetPadding().xxxSmallPadding)
                            )
                        }
                    }
                }

                if (showEpisodes && DataState.isLocal) {
                    if (searchResultState.episodesData?.episodesData != null) {
                        item {
                            Spacer(modifier = Modifier.height(GetPadding().xSmallPadding))
                            Text(
                                text = R.string.episodes_screen_title.toString(),
                                Modifier
                                    .background(Color.LightGray)
                                    .fillMaxWidth()
                                    .padding(GetPadding().xxxSmallPadding)
                            )
                        }
                        if (searchResultState.episodesData.episodesData.isEmpty()) {
                            item {
                                Spacer(modifier = Modifier.height(GetPadding().xSmallPadding))
                                Text(
                                    text = "No Episodes found matching: ${query.value.text}",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(GetPadding().xxxSmallPadding)
                                )
                            }
                        } else {
                            items(
                                searchResultState.episodesData.episodesData,
                                key = { it.id.toString() }
                            ) { item ->
                                GetRowWithFourImages(
                                    imageUrlLink = item.images,
                                    titleName = item.name.toString(),
                                    property1 = item.episode.toString(),
                                    property2 = item.air_date.toString(),
                                    id = item.id.toString(),
                                    onClickable = onEpisodeClick
                                )
                            }
                        }

                        if (searchResultState.episodesData.pages?.next != null && !DataState.isLocal) {
                            item {
                                Button(
                                    onClick = updateCharacterList,
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(GetPadding().mediumPadding)
                                ) {
                                    Text(
                                        text =
                                        stringResource(R.string.load_more),
                                        modifier = Modifier
                                            .semantics {
                                                /** Hardcoded string used to testing */
                                                contentDescription = "Load More Characters"
                                            }
                                    )
                                }
                            }
                            item {
                                if (searchResultState.isCharacterUpdateLoading) {
                                    LocationLoaderCells(deviceType)
                                }
                            }
                        }
                    } else {
                        item {
                            Spacer(modifier = Modifier.height(GetPadding().xSmallPadding))
                            Text(
                                text = R.string.episodes_screen_title.toString(),
                                Modifier
                                    .background(Color.LightGray)
                                    .fillMaxWidth()
                                    .padding(GetPadding().xxxSmallPadding)
                            )
                            Spacer(modifier = Modifier.height(GetPadding().xSmallPadding))
                            Text(
                                text = stringResource(R.string.type_to_search_episode_by_overview),
                                Modifier
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
    showEpisodes: Boolean,
    onShowEpisodes: () -> Unit,
    onResetQuery: () -> Unit,
) {
    Row(modifier = Modifier.padding(GetPadding().xSmallPadding)) {
        OutlinedTextField(
            value = query.value.text,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(stringResource(id = R.string.search_bar)),
            textStyle = TextStyle(color = MaterialTheme.colors.onBackground, fontSize = 18.sp),
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(GetPadding().xxxMediumPadding)
                        .size(dimensionResource(id = R.dimen.icon_size))
                )
            },
            trailingIcon = {
                if (query.value != TextFieldValue("")) {
                    IconButton(
                        onClick = onResetQuery
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(GetPadding().xxxMediumPadding)
                                .size(dimensionResource(id = R.dimen.icon_size)),
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }
            },
            singleLine = true,
            shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.onBackground,
                cursorColor = MaterialTheme.colors.onBackground,
                leadingIconColor = MaterialTheme.colors.onBackground,
                trailingIconColor = MaterialTheme.colors.onBackground,
                backgroundColor = MaterialTheme.colors.background,
                focusedIndicatorColor = MaterialTheme.colors.onBackground,
                unfocusedIndicatorColor = MaterialTheme.colors.onBackground,
                disabledIndicatorColor = MaterialTheme.colors.onBackground
            ),
            placeholder = {
                Text(text = stringResource(id = R.string.search_screen_title))
            }
        )
    }
    Column() {
        Row(
            horizontalArrangement = Arrangement.spacedBy(GetPadding().xSmallPadding),
            modifier = Modifier.padding(horizontal = GetPadding().xSmallPadding)
        ) {
            Button(
                onClick = onShowCharacters,
                Modifier
                    .weight(1.0f)
                    .testTag(stringResource(id = R.string.characters_screen_title)),
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
                        contentDescription = stringResource(R.string.selected),
                        Modifier.padding(horizontal = GetPadding().xSmallPadding)
                    )
                } else {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.outline_check_box_outline_blank_24
                        ),
                        contentDescription = stringResource(R.string.selected),
                        Modifier.padding(horizontal = GetPadding().xSmallPadding)
                    )
                }
                Text(
                    text =
                    stringResource(id = R.string.characters_screen_title)
                )
            }
            Button(
                onClick = onShowLocations,
                Modifier
                    .weight(1.0f)
                    .testTag(stringResource(id = R.string.locations_screen_title)),
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
                        contentDescription = stringResource(id = R.string.selected),
                        Modifier.padding(horizontal = GetPadding().xSmallPadding)
                    )
                } else {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id =
                            R.drawable.outline_check_box_outline_blank_24
                        ),
                        contentDescription = stringResource(id = R.string.selected),
                        Modifier.padding(horizontal = GetPadding().xSmallPadding)
                    )
                }
                Text(text = stringResource(id = R.string.locations_screen_title))
            }
        }
        if (DataState.isLocal) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(GetPadding().xSmallPadding),
                modifier = Modifier.padding(horizontal = GetPadding().xSmallPadding)
            ) {
                Button(
                    onClick = onShowEpisodes,
                    Modifier
                        .weight(1.0f)
                        .testTag(stringResource(id = R.string.characters_screen_title)),
                    colors = if (showEpisodes) {
                        ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
                    } else {
                        ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
                    }
                ) {
                    if (showEpisodes) {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = R.drawable.outline_check_box_24
                            ),
                            contentDescription = stringResource(R.string.selected),
                            Modifier.padding(horizontal = GetPadding().xSmallPadding)
                        )
                    } else {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = R.drawable.outline_check_box_outline_blank_24
                            ),
                            contentDescription = stringResource(R.string.selected),
                            Modifier.padding(horizontal = GetPadding().xSmallPadding)
                        )
                    }
                    Text(
                        text = R.string.episodes_screen_title.toString()
                    )
                }
            }
        }
    }
}

object SearchDestination : NavigationDestination {
    override val route = "search"
    override val screenTitleRes = R.string.search_screen_title
}