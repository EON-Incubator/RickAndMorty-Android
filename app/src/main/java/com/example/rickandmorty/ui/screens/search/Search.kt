package com.example.rickandmorty.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithFourImages

@Composable
fun Search(
    characterState: SearchViewModel.CharacterState,
    locationState: SearchViewModel.LocationState,
    onValueChange: (name: String) -> Unit,
    query: TextFieldValue,
    onLocationClick: (id: String) -> Unit,
    onCharacterClick: (id: String) -> Unit,
) {
    Column() {
        TextField(
            value = query.text,
            onValueChange = onValueChange
        )

        LazyColumn {
            item {
                Text(text = "Characters")
            }
            if (characterState.characters.isNotEmpty()) {
                items(characterState.characters) { item ->
                    Card(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp)),
                        elevation = 12.dp
                    ) {
                        Box(contentAlignment = Alignment.BottomCenter) {
                            AsyncImage(
                                model = item.image,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clickable { onCharacterClick(item.ID.toString()) },
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = item.name.toString(),
                                modifier = Modifier
                                    .background(MaterialTheme.colors.primaryVariant)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.onPrimary,
                                style = MaterialTheme.typography.body1
                            )
                        }
                    }
                }
            }
            item {
                Text(text = "Locations")
            }

            if (locationState.locations.isNotEmpty()) {
                items(locationState.locations) { item ->
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

object SearchDestination : NavigationDestination {
    override val route = "search"
    override val screenTitleRes = R.string.search_screen_title
}