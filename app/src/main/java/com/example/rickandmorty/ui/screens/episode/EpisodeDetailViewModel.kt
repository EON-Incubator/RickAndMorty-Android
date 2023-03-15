package com.example.rickandmorty.ui.screens.episode

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.episodeusecase.GetEpisodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
                selectedEpisode = getEpisodeUseCase.execute(id.toString()),
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

    data class DetailEpisodesState(
        val isLoading: Boolean = false,
        val selectedEpisode: DetailedEpisode? = null,
    )
}