package com.example.rickandmorty.ui.screens.character

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.domain.episodes.Episodes
import com.example.rickandmorty.ui.screens.commonUtils.GetDimensions
import com.example.rickandmorty.ui.screens.commonUtils.shimmerBackground

@Composable
fun CharacterLoader(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(GetDimensions().smallPadding)
            .semantics { contentDescription = "Fetching Characters" }
    ) {
        repeat(8) {
            item {
                Card(
                    modifier = Modifier
                        .padding(GetDimensions().xMediumPadding)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { },
                    elevation = 12.dp
                ) {
                    Box(contentAlignment = Alignment.BottomCenter) {
                        AsyncImage(
                            model = "",
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(
                                    RoundedCornerShape(8.dp)
                                )
                                .shimmerBackground(RoundedCornerShape(40.dp))
                                .size(150.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DetailedCharacterLoader(modifier: Modifier = Modifier) {
    repeat(8) {
        Column() {
            CharacterDetails(
                state = DetailedCharacterViewModel.detailedcharacterState(
                    DetailedCharacter(
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        listOf<Episodes>(),
                        "",
                        "",
                        "",
                        ""

                    ),
                    isLoading = false
                ),
                navigateUp = { /*TODO*/ },
                onEpisodeClick = {},
                onOriginClick = {},
                onLastSeenClick = {},
                modifier = Modifier.shimmerBackground()
            )
        }
    }
}