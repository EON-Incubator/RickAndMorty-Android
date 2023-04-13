package com.example.rickandmorty.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.rickandmorty.RickAndMortyApp
import com.example.rickandmorty.domain.character.GetCharacterUseCase
import com.example.rickandmorty.domain.location.GetAllLocationUseCase
import com.example.rickandmorty.domain.search.GetSearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModelProvider @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase,
    private val getAllLocationUseCase: GetAllLocationUseCase,
    private val getCharacterUseCase: GetCharacterUseCase,
) {
    init {

        refresh()
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.rickApplication(): RickAndMortyApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RickAndMortyApp)