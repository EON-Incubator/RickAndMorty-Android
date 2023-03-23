package com.example.rickandmorty.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.rickandmorty.domain.character.CharacterData
import com.example.rickandmorty.domain.search.GetSearchResultUseCase
import com.example.rickandmorty.domain.location.LocationData
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase,
) :
    ViewModel() {

    private val _searchResult = MutableStateFlow(SearchResult())
    val searchResult = _searchResult.asStateFlow()

    var query by mutableStateOf(TextFieldValue(""))

    fun onSearch(name: String) {
        query = TextFieldValue(name.toString())
        if (query.text.length > 2) {
            viewModelScope.launch(Dispatchers.IO) {
                _searchResult.update {
                    it.copy(
                        isLoading = true
                    )
                }
                val characterData = getSearchResultUseCase.execute(name)
//                Log.v("Search test: View Model", characterData.toString())

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

    data class SearchResult(
        val characterData: CharacterData? = null,
        val locationByName: LocationData? = null,
        val locationByType: LocationData? = null,
        var isLoading: Boolean = false,
    )
}