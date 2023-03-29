package com.example.rickandmorty.ui.screens.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.character.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.rickandmorty.domain.character.DetailedCharacter
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class DetailedCharacterViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val id = savedStateHandle.get<String>("id")
    private val _character = MutableStateFlow(detailedcharacterState())
    val character = _character.asStateFlow()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    init {

        refresh()
    }

    fun refresh() {

        viewModelScope.launch {
            _character.update {
                it.copy(
                    isLoading = true
                )
            }
            _character.update {
                it.copy(
                    character = getCharacterUseCase.specificCharacter(id.toString()),
                    isLoading = false
                )
            }
            _isRefreshing.emit(false)
        }
    }

    data class detailedcharacterState(
        val character: DetailedCharacter? = null,
        val isLoading: Boolean = false,

        )
}