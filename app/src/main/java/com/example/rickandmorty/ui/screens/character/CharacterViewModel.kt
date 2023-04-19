package com.example.rickandmorty.ui.screens.character

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.Optional
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.domain.character.DetailedCharacter

import com.example.rickandmorty.domain.character.GetCharacterUseCase
import com.example.rickandmorty.network.ConnectivityObserver
import com.example.type.FilterCharacter

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
With the AndroidEntryPoint Annotation
Hilt comes to we know we gonna use some kind of dependency
injection in it
 */
/**
we are performing field injection with inject
late init var is used to initialize it later
 **/

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
//    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

//    val internetStatus = savedStateHandle.get<ConnectivityObserver.Status>("status")
    private val _characters = MutableStateFlow(CharacterState())
    val characters = _characters.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

//    private val _internetConnection = MutableStateFlow(ConnectivityObserver.Status.Lost)
//    val internetConnection: StateFlow<ConnectivityObserver.Status>
//        get() = _internetConnection.asStateFlow()

    private val _filterCharacter = MutableStateFlow(
        FilterCharacter(
            Optional.presentIfNotNull(""),
            Optional.presentIfNotNull(""),
            Optional.presentIfNotNull(""),
            Optional.presentIfNotNull("")
        )
    )
    val filterCharacter = _filterCharacter.asStateFlow()
    var gender by mutableStateOf("")
    var status by mutableStateOf("")

    init {
//        Log.v("CharacterViewModel", internetStatus?.name.toString())
        refresh()
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.update {
                it.copy(
                    isLoading = true
                )
            }
            val characterData = getCharacterUseCase.sortById(filterCharacter.value, internetStatus = _characters.value.internetStatus)

            _characters.update {
                it.copy(
                    characters = characterData?.characters ?: emptyList(),
                    isLoading = false,
                    pages = characterData?.pages
                )
            }
            _isRefreshing.emit(false)
        }
    }

    fun changeGender(selectedGender: String) {
        gender = selectedGender
    }

    fun changeStatus(selectedStatus: String) {
        status = selectedStatus
    }

    open fun selectFilter() {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.update {
                it.copy(
                    isLoading = true
                )
            }
            _filterCharacter.update {
                it.copy(

                    status = Optional.presentIfNotNull(
                        if (status != "All") {
                            status
                        } else {
                            ""
                        }
                    ),
                    gender = Optional.presentIfNotNull(
                        if (gender != "All") {
                            gender
                        } else {
                            ""
                        }
                    )
                )
            }

            val characterData =
                getCharacterUseCase.sortById(
                    filterCharacter.value,
                    page = 1,
                    internetStatus = _characters.value.internetStatus
                )
            Log.v(R.string.values.toString(), filterCharacter.value.toString())
            Log.v(R.string.values.toString(), characters.value.toString())
            _characters.update {
                it.copy(
                    characters = characterData?.characters ?: emptyList(),
                    pages = characterData?.pages,
                    isLoading = false
                )
            }
        }
    }

    fun updateList() {
        viewModelScope.launch(Dispatchers.IO) {
            if (characters.value.pages?.next != null) {
                _characters.update {
                    it.copy(
                        isLoadingPage = true
                    )
                }
            }

            val characterData =
                getCharacterUseCase.sortById(
                    filterCharacter.value,
                    page = characters.value.pages?.next ?: 1,
                    internetStatus = _characters.value.internetStatus
                )
            _characters.update {
                it.copy(
                    characters = it.characters + (characterData?.characters ?: emptyList()),
                    pages = characterData?.pages,
                    isLoadingPage = false
                )
            }
        }
    }

    fun setStatus(internetStatus: ConnectivityObserver.Status) {
        if (_characters.value.internetStatus != internetStatus) {
            viewModelScope.launch(Dispatchers.IO) {
                _characters.update {
                    it.copy(
                        internetStatus = internetStatus
                    )
                }
            }
//            Log.v("CharacterViewModel setStatus", internetStatus.name.toString())
//            refresh()
        }
    }
    data class CharacterState(
        val characters: List<Character> = emptyList(),
        val character: DetailedCharacter? = null,
        val isLoading: Boolean = false,
        var selectedCharacter: String? = null,
        var pages: Paginate? = null,
        val isLoadingPage: Boolean = false,
        val internetStatus: ConnectivityObserver.Status = ConnectivityObserver.Status.Lost,
    )
}