package com.example.rickandmorty.ui.screens.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.Episodes
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
            _episode.update {
                it.copy(
                    isLoading = true
                )
            }
            getAllEpisode()
        }
    }

    private suspend fun getAllEpisode() {
        _episode.update {
            it.copy(
                episodes = getAllEpisodeUseCase.execute(),
                isLoading = false
            )
        }
    }

//    fun selectEpisode(id: String) {
//        viewModelScope.launch {
//            _state.update {
//                it.copy(
//                    selectedEpisode = getEpisodeUseCase.execute(id)
//                )
//            }
//        }
//    }

    fun dismissEpisodeDialog() {
        _episode.update {
            it.copy(
                selectedEpisode = null
            )
        }
    }

    data class EpisodesState(
        val episodes: List<Episodes> = emptyList(),
        val isLoading: Boolean = false,
        val selectedEpisode: DetailedEpisode? = null,
    )
}