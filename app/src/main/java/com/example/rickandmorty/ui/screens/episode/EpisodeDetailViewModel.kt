package com.example.rickandmorty.ui.screens.episode

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.api.APIService
import com.example.rickandmorty.domain.episodes.DetailedEpisode
import com.example.rickandmorty.domain.episodes.GetEpisodeUseCase
import com.example.rickandmorty.domain.episodes.ImageData
import com.example.rickandmorty.domain.episodes.TmdbEpisodeDetail
import com.example.rickandmorty.network.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    val getEpisodeUseCase: GetEpisodeUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val id = savedStateHandle.get<String>("id")
    private val _episode = MutableStateFlow(DetailEpisodesState())
    val state = _episode.asStateFlow()
    private val _playVideo = MutableStateFlow(false)
    val playVideo = _playVideo.asStateFlow()

    private val _episodeDetail = MutableStateFlow(TmdbEpisodeDetail())
    var errorMessage: String by mutableStateOf("")
    val episodeDetail = _episodeDetail.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            _episode.update {
                it.copy(
                    isLoading = true
                )
            }
            getEpisode()
            val apiService = APIService.getInstance()
            try {
                _episodeDetail.update {
                    apiService.getEpisodeDetails(
                        season = state.value.selectedEpisode?.episode?.substring(range = 1..2)
                            ?.toInt() ?: 0,
                        episode = state.value.selectedEpisode?.episode?.substring(4)?.toInt() ?: 0
                    )
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
            Log.v("DAta", episodeDetail.value.toString())
        }
    }
    suspend fun getEpisode() {
        _episode.update {
            it.copy(
                selectedEpisode = getEpisodeUseCase.execute(id.toString(), _episode.value.internetStatus),
                isLoading = false
            )
        }
    }

    private suspend fun getCharacters() {
        _episode.update {
            it.copy(
                selectedEpisode = getEpisodeUseCase.execute(
                    id.toString(),
                    _episode.value.internetStatus
                ),
                isLoading = false
            )
        }
    }

    fun setStatus(internetStatus: ConnectivityObserver.Status) {
        if (_episode.value.internetStatus != internetStatus) {
            viewModelScope.launch(Dispatchers.IO) {
                _episode.update {
                    it.copy(
                        internetStatus = internetStatus
                    )
                }
                getData()
            }
        }
    }

    fun getEpisodeOverview(): String {
        return episodeDetail.value.overview
    }

    fun getEpisodeRating(): String {
        return (episodeDetail.value.voteAverage ?: 0.0f).toString()
    }

    fun getEpisodeImages(): List<ImageData> {
        return episodeDetail.value.images?.stills ?: emptyList()
    }

    fun getEpisodeVideo(): String {
        return episodeDetail.value.videos?.results?.firstOrNull()?.key ?: ""
    }

    data class DetailEpisodesState(
        val characters: List<com.example.rickandmorty.domain.character.Character> = emptyList(),
        val isLoading: Boolean = false,
        val selectedEpisode: DetailedEpisode? = null,
        val internetStatus: ConnectivityObserver.Status = ConnectivityObserver.Status.Lost,
    )
}