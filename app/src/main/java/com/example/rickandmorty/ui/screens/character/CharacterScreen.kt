package com.example.rickandmorty.ui.screens.character

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.rickandmorty.domain.character.Character

@Composable
fun Characters(
    state: CharacterViewModel.characterState,

    onClick: (id: String) -> Unit,
    onCharacterClick: (code: String) -> Unit,
) {
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
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(8.dp)
            ) {
                items(state.characters) { character ->
                    characterItem(
                        charstate = character,
                        onClick = onClick,
                        onCharacterClick = onCharacterClick
                    )
                }
            }
        }
    }
}

object CharacterDestination : NavigationDestination {
    override val route = "characters"
    override val screenTitleRes = R.string.characters_screen_title
}

private fun action1(charstate: Character, onClick: (id: String) -> Unit) {
    onClick(charstate.ID.toString())
}

private fun action2(charstate: Character, changeId: (code: String) -> Unit) {
    changeId(charstate.ID.toString())
}

@Composable
private fun characterItem(
    charstate: Character,

    onClick: (id: String) -> Unit,
    onCharacterClick: (code: String) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                action1(charstate = charstate, onClick)
                action2(charstate = charstate, onCharacterClick)
            },
        elevation = 12.dp
    ) {
        Box(contentAlignment = Alignment.BottomCenter) {
            AsyncImage(
                model = charstate.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(8.dp)
                    ),
                contentScale = ContentScale.Crop
            )
            Text(
                text = charstate.name.toString(),
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