package com.example.rickandmorty.ui.screens.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.Episodes
import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.domain.episodeusecase.GetAllEpisodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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

    init {
        viewModelScope.launch {
            val episodeDataById = getAllEpisodeUseCase.sortEpisodeById()
            allEpisode(isLoading = true)
            allEpisode(
                episodes = episodeDataById.episodesData ?: emptyList(),
                isLoading = false,
                pages = episodeDataById.pages
            )
//            val episodeDataByName = getAllEpisodeUseCase.execute()
//            allEpisode(episodes = episodeDataByName, isLoading = false)
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
//                allEpisode(isLoading = true)
                val episodeDataById = getAllEpisodeUseCase
                    .sortEpisodeById(page = state.value.pages?.next ?: 1)

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

    fun allEpisode(
        episodes: List<Episodes> = emptyList(),
        isLoading: Boolean = false,
        pages: Paginate? = null,
        selectedEpisode: DetailedEpisode? = null,
    ) {
        _episode.update {
            it.copy(
                episodes = episodes,
                isLoading = isLoading,
                pages = pages,
                selectedEpisode = selectedEpisode
            )
        }
    }

    data class EpisodesState(
        val episodes: List<Episodes> = emptyList(),
        val isLoading: Boolean = false,
        val selectedEpisode: DetailedEpisode? = null,
        var pages: Paginate? = null,
    )
}