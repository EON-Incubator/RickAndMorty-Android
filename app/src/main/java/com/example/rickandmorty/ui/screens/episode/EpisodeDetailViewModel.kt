package com.example.rickandmorty.ui.screens.episode

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.episodes.DetailedEpisode
import com.example.rickandmorty.domain.episodes.GetEpisodeUseCase
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

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _episode.update {
                it.copy(
                    isLoading = true
                )
            }
            getEpisode()
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

    data class DetailEpisodesState(
        val characters: List<com.example.rickandmorty.domain.character.Character> = emptyList(),
        val isLoading: Boolean = false,
        val selectedEpisode: DetailedEpisode? = null,
    )
}