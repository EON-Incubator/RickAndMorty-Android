package com.example.rickandmorty.ui.screens.location

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.domain.location.GetAllLocationUseCase
import com.example.rickandmorty.domain.location.Location
import com.example.rickandmorty.network.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    // Initialization of components after launching
    init {

        refresh()
    }

    fun refresh() {
        _locations.update {
            it.copy(isLoadingPage = true)
        }

        viewModelScope.launch(Dispatchers.IO) {
            val locationData = getAllLocationUseCase.execute(internetStatus = _locations.value.internetStatus)
            _locations.update {
                it.copy(
                    locations = locationData?.locations ?: emptyList(),
                    isLoadingPage = false,
                    pages = locationData?.pages
                )
            }
            _isRefreshing.emit(false)
        }
    }

    /**
     * Coroutine function that gets All Location from GraphQL
     */
    fun updateList() {
        viewModelScope.launch(Dispatchers.IO) {
            if (location.value.pages?.next != null) {
                _locations.update {
                    it.copy(isLoading = true)
                }

                val locationData = getAllLocationUseCase.execute(page = location.value.pages?.next ?: 1, internetStatus = _locations.value.internetStatus)
                _locations.update {
                    it.copy(
                        locations = it.locations + (locationData?.locations ?: emptyList()),
                        pages = locationData?.pages,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun setStatus(internetStatus: ConnectivityObserver.Status) {
        if (_locations.value.internetStatus != internetStatus) {
            viewModelScope.launch(Dispatchers.IO) {
                _locations.update {
                    it.copy(
                        internetStatus = internetStatus
                    )
                }
                refresh()
            }
        }
    }

    data class LocationUiState(
        val locations: List<Location> = emptyList(),
        val isLoading: Boolean = false,
        var pages: Paginate? = null,
        val isLoadingPage: Boolean = false,
        val internetStatus: ConnectivityObserver.Status = ConnectivityObserver.Status.Lost,
    )
}

// @OptIn(ExperimentalCoroutinesApi::class)
// @Composable
// fun getCurrentConnectionStatus(): Boolean {
//    val connection by connectivityState()
//    return connection === ConnectionState.Available
// }