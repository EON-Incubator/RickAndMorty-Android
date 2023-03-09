package com.example.rickandmorty.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.navigation.RickAndMortyNavHost

@Composable
fun RickAndMortyMainApp(navController: NavHostController = rememberNavController()){
    RickAndMortyNavHost(navController = navController)
}