package com.example.rickandmorty.domain.location

import com.example.rickandmorty.domain.CharacterClient

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