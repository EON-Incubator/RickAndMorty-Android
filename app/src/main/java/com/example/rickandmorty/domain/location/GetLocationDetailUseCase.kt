package com.example.rickandmorty.domain.location

import com.example.rickandmorty.domain.CharacterClient

/**
 * Class that gets the Data from CharacterClients
 * and perform functions such as
 * sorting and filtering
 **/
class GetLocationDetailUseCase(
    private val characterClient: CharacterClient,
) {

    suspend fun execute(id: String): LocationDetail? {
        return characterClient
            .getLocationDetail(id)
    }
}