package com.example.rickandmorty.ui.screens.character

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination

@Composable
fun Characters(){
    Text(text = "Characters")
}
object CharacterDestination : NavigationDestination {
    override val route = "home"
    override val screenTitleRes = R.string.characters_screen_title
}