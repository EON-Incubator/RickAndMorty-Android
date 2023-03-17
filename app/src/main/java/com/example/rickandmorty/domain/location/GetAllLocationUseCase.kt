package com.example.rickandmorty.domain.location

import com.example.rickandmorty.domain.CharacterClient
import com.example.type.FilterLocation

/**
 * Class that gets the Data from CharacterClients
 * and perform functions such as
 * sorting and filtering
 **/
class GetAllLocationUseCase(
    private val characterClient: CharacterClient,
) {

    suspend fun execute(filterLocation: FilterLocation = FilterLocation()): List<Location> {
        return characterClient
            .getAllLocations(filterLocation)
    }

    suspend fun sortByName(filterLocation: FilterLocation = FilterLocation()): List<Location> {
        return characterClient
            .getAllLocations(filterLocation)
            .sortedBy { it.name }
    }
}