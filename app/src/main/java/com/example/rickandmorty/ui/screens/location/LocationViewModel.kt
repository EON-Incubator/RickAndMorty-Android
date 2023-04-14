package com.example.rickandmorty.ui.screens.location

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.realm.data.MongoRepository
import com.example.rickandmorty.data.realm.schema.Character
import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.domain.location.GetAllLocationUseCase
import com.example.rickandmorty.domain.location.Location
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
    private val repository: MongoRepository,
) : ViewModel() {

    // Mutable Flow State variables
    private val _locations = MutableStateFlow(LocationUiState())
    val location = _locations.asStateFlow()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()
    var data = mutableStateOf(emptyList<com.example.rickandmorty.data.realm.schema.Character>())

    // Initialization of components after launching
    init {

        refresh()
        var character: com.example.rickandmorty.data.realm.schema.Character = Character().apply {
            name = "monty"
            type = "type"
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertPerson(character)
        }

        viewModelScope.launch {
            repository.getData().collect {
                data.value = it
                Log.v("Realm Data", it[0].name.toString())
            }
        }
    }

    fun refresh() {
        _locations.update {
            it.copy(isLoadingPage = true)
        }

        viewModelScope.launch(Dispatchers.IO) {
            val locationData = getAllLocationUseCase.execute()
            _locations.update {
                it.copy(
                    locations = locationData.locations ?: emptyList(),
                    isLoadingPage = false,
                    pages = locationData.pages
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

                val locationData = getAllLocationUseCase.execute(page = location.value.pages?.next ?: 1)
                _locations.update {
                    it.copy(
                        locations = it.locations + (locationData.locations ?: emptyList()),
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
        val isLoadingPage: Boolean = false,
    )
}