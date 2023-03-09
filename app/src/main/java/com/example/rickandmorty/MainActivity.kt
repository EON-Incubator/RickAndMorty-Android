package com.example.rickandmorty

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.rickandmorty.api.ApolloModule
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            //to use provider directly
            val characterClient = ApolloModule.provideGetCharactersClient(ApolloModule.provideApolloClient())
            Log.v("Test",characterClient.getCharacters().toString())

            //to query using Use Case(Clean Architecture)
            //sorted result by name
            val characterClientUseCase = ApolloModule.provideGetCharacterUseCase(characterClient = characterClient)
            Log.v("Test",characterClientUseCase.execute().toString())
        }
        setContent {
            RickAndMortyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}
