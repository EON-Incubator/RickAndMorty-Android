package com.example.rickandmorty.ui.screens.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.domain.character.Character

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

@HiltViewModel
class CharacterViewModel @Inject constructor(private val getCharacterUseCase: GetCharacterUseCase) :
    ViewModel() {

    private val _characters = MutableStateFlow(CharacterState())
    val characters = _characters.asStateFlow()

    init {
        viewModelScope.launch {
            _characters.update {
                it.copy(
                    isLoading = true
                )
            }
            val characterData = getCharacterUseCase.sortById()
            _characters.update {
                it.copy(
                    characters = characterData.characters ?: emptyList(),
                    isLoading = false,
                    pages = characterData.pages
                )
            }
        }
    }

    open fun selectCountry(code: String) {
        viewModelScope.launch {
            _characters.update {
                it.copy(
                    character = getCharacterUseCase.specificCharacter(code)
                )
            }
        }
    }

    fun updateList() {
        viewModelScope.launch {
            if (characters.value.pages?.next != null) {
                _characters.update {
                    it.copy(
                        isLoading = true
                    )
                }

                val characterData =
                    getCharacterUseCase.sortById(page = characters.value.pages?.next ?: 1)
                _characters.update {
                    it.copy(
                        characters = it.characters + (characterData.characters ?: emptyList()),
                        pages = characterData.pages,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun dismissCharacterDialog() {
    }

    data class CharacterState(
        val characters: List<Character> = emptyList(),
        val character: com.example.rickandmorty.domain.character.DetailedCharacter? = null,
        val isLoading: Boolean = false,
        var selectedCharacter: String? = null,
        var pages: Paginate? = null,
    )
}