package com.example.rickandmorty.ui.screens.search

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination


@Composable
fun Search(){
    Text(text = "Search")
}
object SearchDestination : NavigationDestination {
    override val route = "search"
    override val screenTitleRes = R.string.search_screen_title
}