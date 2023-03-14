package com.example.rickandmorty.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.character.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.domain.location.GetAllLocationUseCase
import com.example.rickandmorty.domain.location.Location

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getAllLocationUseCase: GetAllLocationUseCase,
) :
    ViewModel() {

    private val _characters = MutableStateFlow(CharacterState())
    val characters = _characters.asStateFlow()
    private val _locations = MutableStateFlow(LocationState())
    val locations = _locations.asStateFlow()
    var query by mutableStateOf(TextFieldValue(""))

    fun onSearch(name: String) {
        query = TextFieldValue(name)
        if (query.text.length > 2) {
            viewModelScope.launch {
                _characters.update {
                    it.copy(
                        characters = getCharacterUseCase.sortById(name)
                    )
                }
                _locations.update {
                    it.copy(
                        locations = getAllLocationUseCase.sortByName(name)
                    )
                }
            }
        }
    }

    data class CharacterState(
        val characters: List<Character> = emptyList(),
        val isLoading: Boolean = false,
    )

    data class LocationState(
        val locations: List<Location> = emptyList(),
        val isLoading: Boolean = false,
    )
}