package com.example.rickandmorty.domain.location

import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.network.ConnectivityObserver

/**
 * Class that gets the Data from CharacterClients
 * and perform functions such as
 * sorting and filtering
 **/
class GetLocationDetailUseCase(
    private val characterClient: CharacterClient,
) {

    suspend fun execute(
        id: String,
        internetStatus: ConnectivityObserver.Status = ConnectivityObserver.Status.Lost,
    ): LocationDetail? {
        return characterClient
            .getLocationDetail(id)
    }
}