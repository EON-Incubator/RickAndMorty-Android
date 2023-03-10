package com.example.rickandmorty.ui.screens.character

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

/*

Testing
 */

@Composable
fun CharactersScreen(
    state: CharacterViewModel.characterState,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)

            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.characters) { character ->
                    characterItem(
                        character = character,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun characterItem(
    character: com.example.rickandmorty.domain.character.Character,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(model = character.image, contentDescription = null)
        Text(text = character.ID.toString(), fontSize = 24.sp)
    }
}