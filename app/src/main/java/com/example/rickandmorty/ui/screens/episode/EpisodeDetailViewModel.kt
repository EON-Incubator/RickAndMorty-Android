package com.example.rickandmorty.ui.screens.episode

import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.episodes.TmdbEpisodeDetail
import com.example.rickandmorty.api.APIService
import com.example.rickandmorty.domain.episodes.DetailedEpisode
import com.example.rickandmorty.domain.episodes.GetEpisodeUseCase
import com.example.rickandmorty.domain.episodes.ImageData
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
                        state.value.selectedEpisode?.episode?.substring(4)?.toInt() ?: 0,
                        state.value.selectedEpisode?.episode?.substring(range = 1..2)
                            ?.toInt() ?: 0
                    )
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    suspend fun getEpisode() {
        _episode.update {
            it.copy(
                selectedEpisode = getEpisodeUseCase.execute(id.toString()),
                isLoading = false
            )
        }
    }

    private suspend fun getCharacters() {
        _episode.update {
            it.copy(
                selectedEpisode = getEpisodeUseCase.execute(id.toString()),
                isLoading = false
            )
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

    data class DetailEpisodesState(
        val characters: List<com.example.rickandmorty.domain.character.Character> = emptyList(),
        val isLoading: Boolean = false,
        val selectedEpisode: DetailedEpisode? = null,
    )
}