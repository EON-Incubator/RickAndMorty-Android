package com.example.rickandmorty.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.rickandmorty.RickAndMortyApp
import com.example.rickandmorty.api.APIService
import com.example.rickandmorty.data.local.repository.CharactersRepository
import com.example.rickandmorty.data.local.repository.EpisodesRepository
import com.example.rickandmorty.data.local.repository.LocationsRepository
import com.example.rickandmorty.data.local.schema.Character
import com.example.rickandmorty.data.local.schema.Episode
import com.example.rickandmorty.data.local.schema.Location
import com.example.rickandmorty.domain.localRealm.GetAllDataUseCase
import com.example.rickandmorty.network.ConnectivityObserver
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

    init {
    }

    private fun LoadAllData() {
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
                val apiService = APIService.getInstance()
                var episodeDetail: com.example.rickandmorty.domain.episodes.TmdbEpisodeDetail =
                    com.example.rickandmorty.domain.episodes.TmdbEpisodeDetail()
                try {
                    episodeDetail =
                        apiService.getEpisodeDetails(
                            season = it?.episode?.substring(range = 1..2)
                                ?.toInt() ?: 0,
                            episode = it?.episode?.substring(4)?.toInt() ?: 0
                        )
                } catch (e: Exception) {
                    Log.v("Room Error", e.message.toString())
                }
                episodesRepository.insertEpisode(
                    Episode(
                        id = it.id ?: "",
                        name = it.name ?: "",
                        episode = it.episode ?: "",
                        air_date = it.air_date ?: "",
                        characters = it?.characters ?: emptyList(),
                        episodeDetail = episodeDetail
                    )
                )
            }
            Log.v("Completed", "True")
            DataState.isLocal = true
        }
    }

    fun setStatus(internetStatus: ConnectivityObserver.Status) {
        if (internetStatus == ConnectivityObserver.Status.Available) {
            LoadAllData()
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.rickApplication(): RickAndMortyApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RickAndMortyApp)

object DataState {
    var isLocal: Boolean = false
}