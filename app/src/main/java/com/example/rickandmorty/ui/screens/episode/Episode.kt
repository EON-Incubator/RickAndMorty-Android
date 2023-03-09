package com.example.rickandmorty.ui.screens.episode

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination

@Composable
fun Episodes(){
    Text(text = "Episodes")
}
object EpisodeDestination : NavigationDestination {
    override val route = "episodes"
    override val screenTitleRes = R.string.episodes_screen_title
}