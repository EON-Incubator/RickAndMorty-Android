package com.example.rickandmorty.ui.screens.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.domain.location.GetAllLocationUseCase
import com.example.rickandmorty.domain.location.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Class that operates the Location Screen UI
 * DI: Hilt
 */
@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getAllLocationUseCase: GetAllLocationUseCase,
) : ViewModel() {

    // Mutable Flow State variables
    private val _locations = MutableStateFlow(LocationUiState())
    val location = _locations.asStateFlow()

    // Initialization of components after launching
    init {

        _locations.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            val locationData = getAllLocationUseCase.execute()
            _locations.update {
                it.copy(
                    locations = locationData.location ?: emptyList(),
                    isLoading = false,
                    pages = locationData.pages
                )
            }
        }
    }

    /**
     * Coroutine function that gets All Location from GraphQL
     */
    fun updateList() {
        viewModelScope.launch {
            if (location.value.pages?.next != null) {
                _locations.update {
                    it.copy(isLoading = true)
                }

                val locationData = getAllLocationUseCase.execute(page = location.value.pages?.next ?: 1)
                _locations.update {
                    it.copy(
                        locations = it.locations + (locationData.location ?: emptyList()),
                        pages = locationData.pages,
                        isLoading = false
                    )
                }
            }
        }
    }

    data class LocationUiState(
        val locations: List<Location> = emptyList(),
        val isLoading: Boolean = false,
        var pages: Paginate? = null,
    )
}