package com.example.rickandmorty.ui.screens.search

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithFourImages
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithOneImage

@Composable
fun Search(
    searchResultState: SearchViewModel.SearchResult,
    onValueChange: (name: String) -> Unit,
    query: TextFieldValue,
    onLocationClick: (id: String) -> Unit,
    onCharacterClick: (id: String) -> Unit,
    onShowCharacters: () -> Unit,
    showCharacters: Boolean,
    onShowLocations: () -> Unit,
    showLocations: Boolean,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(5.dp)) {
            OutlinedTextField(
                value = query.text,
                onValueChange = onValueChange,
                Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Search Bar" }
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(5.dp)
        ) {
            Button(
                onClick = onShowCharacters,
                Modifier.weight(1.0f),
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
                        Modifier.padding(horizontal = 5.dp)
                    )
                } else {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.outline_check_box_outline_blank_24
                        ),
                        contentDescription = "Selected",
                        Modifier.padding(horizontal = 5.dp)
                    )
                }
                Text(text = "Characters (${searchResultState.characterData?.characters?.size ?: 0})")
            }
            Button(
                onClick = onShowLocations,
                Modifier.weight(1.0f),
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
                        Modifier.padding(horizontal = 5.dp)
                    )
                } else {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id =
                            R.drawable.outline_check_box_outline_blank_24
                        ),
                        contentDescription = "Selected",
                        Modifier.padding(horizontal = 5.dp)
                    )
                }
                Text(text = "Locations (${searchResultState.locationByName?.locations?.size ?: 0})")
            }
        }
        if (searchResultState.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .semantics { contentDescription = "Fetching Records" }
                )
            }
        } else {
            LazyColumn {
                if (searchResultState.characterData?.characters != null && showCharacters) {
                    item {
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "Characters",
                            Modifier
                                .background(Color.LightGray)
                                .fillMaxWidth()
                                .padding(2.dp)
                        )
                    }
                    Log.v(
                        "Search test: Screen:",
                        searchResultState.characterData.characters.toString()
                    )
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

                if (searchResultState.locationByName?.locations != null && showLocations) {
                    item {
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "Locations",
                            Modifier
                                .background(Color.LightGray)
                                .fillMaxWidth()
                                .padding(2.dp)
                        )
                    }
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
            }
        }
    }
}

object SearchDestination : NavigationDestination {
    override val route = "search"
    override val screenTitleRes = R.string.search_screen_title
}