package com.example.rickandmorty.ui.screens.location

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.location.GetLocationDetailUseCase
import com.example.rickandmorty.domain.location.LocationDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
/**
 * Class that operates the Location Detail Screen UI
 * DI: Hilt
 */
@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val getLocationDetailUseCase: GetLocationDetailUseCase,
    private val savedStateHandle: SavedStateHandle,

) : ViewModel() {

    // Mutable Flow State variables
    private val _locationDetail = MutableStateFlow(LocationDetailUiState())
    val locationDetail = _locationDetail.asStateFlow()

    val id = savedStateHandle.get<String>("id")

    // Initializer
    init {
        viewModelScope.launch {
            _locationDetail.update {
                it.copy(isLoading = true)
            }
            getLocationDetail(id.toString())
        }
    }

    /**
     * Coroutine function that gets All Location Detail when
     * an id is passed for a specific location
     */
    suspend fun getLocationDetail(id: String) {
        _locationDetail.update {
            it.copy(
                locationDetail = getLocationDetailUseCase.execute(id),
                isLoading = true
            )
        }
    }

    data class LocationDetailUiState(
        val locationDetail: LocationDetail = LocationDetail(
            "",
            "",
            emptyList(),
            ""
        ),
        val isLoading: Boolean = false,
    )
}