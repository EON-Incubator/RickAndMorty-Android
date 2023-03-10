package com.example.rickandmorty.ui.screens.character

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination

@Composable
fun Characters(state: CharacterViewModel.characterState) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn() {
                items(state.characters) { item ->
                    characterItem(item)
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
    AsyncImage(model = state.image, contentDescription = null)
}