package com.example.rickandmorty.ui.screens.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.Episodes
import com.example.rickandmorty.domain.episodeusecase.GetEpisodeUseCase
import com.example.rickandmorty.domain.episodeusecase.GetEpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val getEpisodesUseCase: GetEpisodesUseCase,
    private val getEpisodeUseCase: GetEpisodeUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(EpisodesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            _state.update {
                it.copy(
                    episodes = getEpisodesUseCase.execute(),
                    isLoading = false
                )
            }
        }
    }

    fun selectEpisode(id: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedEpisode = getEpisodeUseCase.execute(id)
                )
            }
        }
    }

    fun dismissEpisodeDialog() {
        _state.update {
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