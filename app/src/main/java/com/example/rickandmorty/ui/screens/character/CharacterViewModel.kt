package com.example.rickandmorty.ui.screens.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.rickandmorty.domain.character.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
With the AndroidEntryPoint Annotation
Hilt comes to we know we gonna use some kind of dependency
injection in it
 */
/*
we are performing field injection with inject
late init var is used to initialize it later
 */
// @AndroidEntryPoint
@HiltViewModel
class CharacterViewModel @Inject constructor(private val getCharacterUseCase: GetCharacterUseCase) :
    ViewModel() {

    private val _characters = MutableStateFlow(characterState())
    val characters = _characters.asStateFlow()

    init {
        viewModelScope.launch {
            _characters.update {
                it.copy(
                    isLoading = true
                )
            }
            _characters.update {
                it.copy(
                    characters = getCharacterUseCase.execute(),
                    isLoading = false

                )
            }
        }
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
        }
    }

    fun dismissCharacterDialog() {
    }

    data class characterState(
        val characters: List<com.example.rickandmorty.domain.character.Character> = emptyList(),
        val isLoading: Boolean = false,
    )
}