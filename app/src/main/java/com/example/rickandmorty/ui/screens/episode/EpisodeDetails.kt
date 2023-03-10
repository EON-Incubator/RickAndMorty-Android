package com.example.rickandmorty.ui.screens.episode

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination

@Composable
fun EpisodeDetails() {
    Text(text = "Episode Details")
}
object EpisodeDetailsDestination : NavigationDestination {
    override val route = "episode_detail"
    override val screenTitleRes = R.string.episode_detail_screen_title
}