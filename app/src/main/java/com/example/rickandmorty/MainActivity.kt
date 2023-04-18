package com.example.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.rickandmorty.network.ConnectivityObserver
import com.example.rickandmorty.network.NetworkConnectivityObserver
import com.example.rickandmorty.ui.screens.RickAndMortyMainApp
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver

    @ExperimentalAnimationApi
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        connectivityObserver.observe().onEach {
            println("Status is $it")
        }.launchIn(lifecycleScope)

        setContent {
            val windowSize = calculateWindowSizeClass(this)
            RickAndMortyTheme() {
                val status by connectivityObserver.observe().collectAsState(
                    initial = ConnectivityObserver.Status.Unavailable
                )
//                Box(modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center)
//                {
//                    Text(text = "Status $status")
//                }
                if (status != ConnectivityObserver.Status.Available) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(text = "Unavailable")
                    }
                } else {
                    RickAndMortyMainApp(windowSize = windowSize)
                }
            }
        }
    }
}