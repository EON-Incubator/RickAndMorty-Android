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

    suspend fun execute(filterLocation: FilterLocation = FilterLocation(), page: Int = 1): LocationData {
        val locationData = characterClient
            .getAllLocations(filterLocation, page)
        return LocationData(
            locations = locationData?.locations,
            pages = locationData?.pages
        )
    }
}