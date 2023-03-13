package com.example.rickandmorty.domain.location

import com.example.rickandmorty.domain.CharacterClient

class GetAllLocationUseCase(
    private val characterClient: CharacterClient,
) {

    suspend fun execute(): List<Location> {
        return characterClient
            .getAllLocations()
    }

    suspend fun sortByName(): List<Location> {
        return characterClient
            .getAllLocations()
            .sortedBy { it.name }
    }
}