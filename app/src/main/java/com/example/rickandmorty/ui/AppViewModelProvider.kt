package com.example.rickandmorty.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.rickandmorty.RickAndMortyApp
import com.example.rickandmorty.domain.character.GetCharacterUseCase
import com.example.rickandmorty.domain.location.GetAllLocationUseCase
import com.example.rickandmorty.domain.search.GetSearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModelProvider @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase,
    private val getAllLocationUseCase: GetAllLocationUseCase,
    private val getCharacterUseCase: GetCharacterUseCase,
): ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {

            var resultData = characterClient.getSearchResult(queryString)
            var characterData = characterClient.getSearchResult(queryString)
            var resultData = characterClient.getSearchResult(queryString)
            var resultData = characterClient.getSearchResult(queryString)
//        var pageNum = page
//        while (
//            resultData?.characterData?.pages?.next != null ||
//            resultData?.locationByName?.pages?.next != null ||
//            resultData?.locationByType?.pages?.next != null
//        ) {
//            Log.v("Search test:Character", resultData?.characterData?.pages?.next.toString())
//            Log.v("Search test: by name", resultData?.locationByName?.pages?.next.toString())
//            Log.v("Search test: by type", resultData?.locationByType?.pages?.next.toString())
//            Log.v("Search test: by pageNum", pageNum.toString())
//            val temp = characterClient.getSearchResult(queryString, ++pageNum)
//            resultData = resultData?.copy(
//                characterData = CharacterData(
//                    characters =
//                    resultData.characterData?.characters?.plus(
//                        temp?.characterData?.characters ?: emptyList()
//                    ),
//                    pages = temp?.characterData?.pages
//                ),
//                locationByName = LocationData(
//                    locations =
//                    resultData.locationByName?.locations?.plus(
//                        temp?.locationByName?.locations ?: emptyList()
//                    ),
//                    pages = temp?.locationByName?.pages
//                ),
//                locationByType = LocationData(
//                    locations = resultData.locationByType?.locations?.plus(
//                        temp?.locationByType?.locations ?: emptyList()
//                    ),
//                    pages = temp?.locationByType?.pages
//                )
//            )
//        }
//        Log.v("Search test:Character", resultData?.characterData?.characters?.size.toString())
//        Log.v("Search test: by name", resultData?.locationByName?.locations?.size.toString())
//        Log.v("Search test: by type", resultData?.locationByType?.locations?.size.toString())

             resultData?.copy(
                locationByName = resultData?.locationByName?.copy(
                    locations = resultData.locationByName?.locations?.plus(
                        resultData?.locationByType?.locations
                            ?: emptyList()
                    )?.distinct()
                )
            )

        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.rickApplication(): RickAndMortyApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RickAndMortyApp)