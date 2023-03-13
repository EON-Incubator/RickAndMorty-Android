package com.example.rickandmorty.domain.location

import com.example.rickandmorty.domain.CharacterClient

class GetLocationDetailUseCase(
    private val characterClient: CharacterClient,
) {

    suspend fun execute(id: String): LocationDetail {
        return characterClient
            .getLocationDetail(id)!!
    }

//    suspend fun sortByName(id: String): List<Location> {
//        return characterClient
//            .getLocationDetail(id)
//            .sortedBy { it.name }
//    }
}