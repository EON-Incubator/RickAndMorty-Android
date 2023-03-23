package com.example.rickandmorty.domain.location

import com.example.rickandmorty.domain.CharacterClient

/**
 * Class that gets the Data from CharacterClients
 * and perform functions such as
 * sorting and filtering
 **/
class GetAllLocationUseCase(
    private val characterClient: CharacterClient,
) {

    suspend fun execute(name: String = ""): List<Location> {
        return characterClient
            .getAllLocations(name)
    }

    suspend fun sortByName(name: String = ""): List<Location> {
        return characterClient
            .getAllLocations(name)
            .sortedBy { it.name }
    }
}