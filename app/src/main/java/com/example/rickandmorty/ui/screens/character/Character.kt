package com.example.rickandmorty.ui.screens.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.commonUtils.ScreenNameBar

@Composable
fun Characters(state: CharacterViewModel.characterState) {

    Column(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            ScreenNameBar(name = "Characters", onFilterClick = {})
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(8.dp)
            ) {
                items(state.characters) { character ->
                    characterItem(state = character)
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
private fun characterItem(state: com.example.rickandmorty.domain.character.Character) {

    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize().clip(RoundedCornerShape(12.dp)),
        elevation = 12.dp
    ) {
        Box(contentAlignment = Alignment.BottomCenter) {

            AsyncImage(
                model = state.image, contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(8.dp)
                    ),
                contentScale = ContentScale.Crop
            )
            Text(
                text = state.name.toString(),
                modifier = Modifier
                    .background(MaterialTheme.colors.primaryVariant)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onPrimary, style = MaterialTheme.typography.body1
            )
        }
    }
}
