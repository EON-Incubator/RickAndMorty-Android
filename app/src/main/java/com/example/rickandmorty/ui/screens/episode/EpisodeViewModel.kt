package com.example.rickandmorty.ui.screens.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.domain.episodes.DetailedEpisode
import com.example.rickandmorty.domain.episodes.Episodes
import com.example.rickandmorty.domain.episodes.GetAllEpisodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val getAllEpisodeUseCase: GetAllEpisodeUseCase,
) : ViewModel() {
    private val _episode = MutableStateFlow(EpisodesState())
    val state = _episode.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        _episode.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val episodeDataById = getAllEpisodeUseCase.sortEpisodeById()
            _episode.update {
                it.copy(
                    episodes = episodeDataById.episodesData ?: emptyList(),
                    isLoading = false,
                    pages = episodeDataById.pages
                )
            }
            _isRefreshing.emit(false)
        }
    }

    fun updateEpisodeList() {
        viewModelScope.launch {
            if (state.value.pages?.next != null) {
                _episode.update {
                    it.copy(
                        isLoading = true
                    )
                }
                val episodeDataById = getAllEpisodeUseCase.sortEpisodeById(page = state.value.pages?.next ?: 1)

                _episode.update {
                    it.copy(
                        episodes = it.episodes + (episodeDataById.episodesData ?: emptyList()),
                        pages = episodeDataById.pages,
                        isLoading = false
                    )
                }
            }
        }
    }

    data class EpisodesState(
        val episodes: List<Episodes> = emptyList(),
        val isLoading: Boolean = false,
        val selectedEpisode: DetailedEpisode? = null,
        var pages: Paginate? = null,
    )
}