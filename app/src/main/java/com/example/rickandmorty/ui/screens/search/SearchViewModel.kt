package com.example.rickandmorty.ui.screens.search

import android.util.Log
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.Optional
import com.example.rickandmorty.domain.character.CharacterData
import com.example.rickandmorty.domain.character.GetCharacterUseCase
import com.example.rickandmorty.domain.location.GetAllLocationUseCase
import com.example.rickandmorty.domain.location.LocationData
import com.example.rickandmorty.domain.search.GetSearchResultUseCase
import com.example.type.FilterCharacter
import com.example.type.FilterLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase,
    private val getAllLocationUseCase: GetAllLocationUseCase,
    private val getCharacterUseCase: GetCharacterUseCase,
) :
    ViewModel() {

    private val _searchResult = MutableStateFlow(SearchResult())
    val searchResult = _searchResult.asStateFlow()

    private var _query = MutableStateFlow(TextFieldValue(""))
    val query = _query.asStateFlow()

    fun onSearch(name: String) {
        _query.debounce(500)
        _query.update {
            TextFieldValue(name)
        }
        if (query.value.text == "") {
            _searchResult.update {
                it.copy(
                    characterData = null,
                    locationByName = null,
                    locationByType = null,
                    isLoading = false
                )
            }
        } else if (query.value.text.length > 2) {
            viewModelScope.launch(Dispatchers.IO) {
                _searchResult.update {
                    it.copy(
                        isLoading = true
                    )
                }
                val characterData = getSearchResultUseCase.execute(name)

                _searchResult.update {
                    it.copy(
                        characterData = characterData?.characterData,
                        locationByName = characterData?.locationByName,
                        locationByType = characterData?.locationByType,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun updateCharacterList() {
        viewModelScope.launch {
            if (searchResult.value.characterData?.pages?.next != null) {
                _searchResult.update {
                    it.copy(
                        isLoading = true
                    )
                }
                val characterData =
                    getCharacterUseCase.sortById(
                        filterCharacter = FilterCharacter(
                            name = Optional.presentIfNotNull(
                                query.value.text
                            )
                        ),
                        page = searchResult.value.characterData?.pages?.next ?: 1
                    )
                _searchResult.update {
                    it.copy(
                        characterData = CharacterData(
                            characters = it.characterData?.characters?.plus(
                                characterData.characters ?: emptyList()
                            ),
                            pages = characterData.pages
                        ),
                        isLoading = false
                    )
                }
            }
        }
    }

    fun updateLocationList() {
        viewModelScope.launch(Dispatchers.IO) {
            if (searchResult.value.locationByName?.pages?.next != null) {
                _searchResult.update {
                    it.copy(
                        isLoading = true
                    )
                }
                Log.v("Debug ME 1", "Running")
                val locationData =
                    getAllLocationUseCase.execute(
                        filterLocation = FilterLocation(
                            name = Optional.presentIfNotNull(
                                query.value.text
                            )
                        ),
                        page = searchResult.value.locationByName?.pages?.next ?: 1
                    )
                _searchResult.update {
                    it.copy(
                        locationByName = LocationData(
                            locations = it.locationByName?.locations?.plus(
                                locationData.locations ?: emptyList()
                            ),
                            pages = locationData.pages
                        ),
                        isLoading = false
                    )
                }
            }
            if (searchResult.value.locationByType?.pages?.next != null) {
                _searchResult.update {
                    it.copy(
                        isLoading = true
                    )
                }
                val locationData =
                    getAllLocationUseCase.execute(
                        filterLocation = FilterLocation(
                            type = Optional.presentIfNotNull(
                                query.value.text
                            )
                        ),
                        page = searchResult.value.locationByType?.pages?.next ?: 1
                    )
                Log.v("Debug ME 2", locationData.toString())
                _searchResult.update {
                    it.copy(
                        locationByType = LocationData(
                            locations = it.locationByType?.locations?.plus(
                                locationData.locations ?: emptyList()
                            ),
                            pages = locationData.pages
                        ),
                        isLoading = false
                    )
                }
            }
            Log.v("Debug ME", _searchResult.value.locationByName.toString())
            _searchResult.update {
                it.copy(
                    locationByName = it.locationByName?.copy(
                        locations = it.locationByName?.locations?.plus(
                            it.locationByType?.locations
                                ?: emptyList()
                        )?.distinct()
                    )
                )
            }
        }
    }

    fun onResetQuery() {
        _query.update {
            TextFieldValue("")
        }
    }

    data class SearchResult(
        val characterData: CharacterData? = null,
        val locationByName: LocationData? = null,
        val locationByType: LocationData? = null,
        var isLoading: Boolean = false,
    )
}