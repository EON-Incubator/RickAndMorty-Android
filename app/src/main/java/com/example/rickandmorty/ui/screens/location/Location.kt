package com.example.rickandmorty.ui.screens.location

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination

@Composable
fun Locations() {
    Text(text = "Locations")
}
object LocationDestination : NavigationDestination {
    override val route = "locations"
    override val screenTitleRes = R.string.locations_screen_title
}