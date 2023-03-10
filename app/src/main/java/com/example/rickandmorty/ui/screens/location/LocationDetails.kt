package com.example.rickandmorty.ui.screens.location

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination

@Composable
fun LocationDetails(){
    Text(text = "Location Details")
}
object LocationDetailsDestination : NavigationDestination {
    override val route = "location_details"
    override val screenTitleRes = R.string.location_detail_screen_title
}