package com.example.rickandmorty.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.rickandmorty.RickAndMortyApp
import com.example.rickandmorty.data.local.repository.CharactersRepository
import com.example.rickandmorty.data.local.repository.EpisodesRepository
import com.example.rickandmorty.data.local.repository.LocationsRepository
import com.example.rickandmorty.data.local.schema.Character
import com.example.rickandmorty.data.local.schema.Episode
import com.example.rickandmorty.data.local.schema.Location
import com.example.rickandmorty.domain.localRealm.GetAllDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModelProvider @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val locationsRepository: LocationsRepository,
    private val episodesRepository: EpisodesRepository,
    private val getAllDataUseCase: GetAllDataUseCase,
) : ViewModel() {
    fun initDelete() {
        viewModelScope.launch(Dispatchers.IO) {
            var resultData = getAllDataUseCase.execute()
            resultData?.characterData?.characters?.forEach {
                charactersRepository.insertCharacter(
                    Character(
                        ID = it.ID ?: "",
                        name = it.name ?: "",
                        image = it.image ?: "",
                        species = it.species ?: "",
                        status = it.status ?: "",
                        gender = it.gender ?: "",
                        lastseen = it.lastseen ?: "",
                        lastseenId = it.lastseenId ?: "",
                        originId = it.originId ?: "",
                        origin = it.origin ?: "",
                        episodes = it.episode
                    )
                )
            }
            resultData?.locationData?.locations?.forEach {
                locationsRepository.insertLocation(
                    Location(
                        id = it.id ?: "",
                        name = it.name ?: "",
                        type = it.type ?: "",
                        dimension = it.dimension ?: "",
                        images = it?.residents?.mapNotNull { it?.image } ?: emptyList(),
                        residents = it?.residents ?: emptyList()
                    )
                )
            }

            resultData?.episodeData?.episodes?.forEach {
                episodesRepository.insertEpisode(
                    Episode(
                        id = it.id ?: "",
                        name = it.name ?: "",
                        episode = it.episode ?: "",
                        air_date = it.air_date ?: "",
                        characters = it?.characters ?: emptyList()
                    )
                )
            }
            Log.v("Completed", "True")
//            var characterData = characterClient.getSearchResult(queryString)
//            var resultData = characterClient.getSearchResult(queryString)
//            var resultData = characterClient.getSearchResult(queryString)
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

//            resultData?.copy(
//                locationByName = resultData?.locationByName?.copy(
//                    locations = resultData.locationByName?.locations?.plus(
//                        resultData?.locationByType?.locations
//                            ?: emptyList()
//                    )?.distinct()
//                )
//            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.rickApplication(): RickAndMortyApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RickAndMortyApp)