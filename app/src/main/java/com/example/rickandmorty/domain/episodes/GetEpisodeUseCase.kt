package com.example.rickandmorty.domain.episodes

import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.network.ConnectivityObserver

class GetEpisodeUseCase(
    private val characterClient: CharacterClient,
) {
    suspend fun execute(
        id: String,
        internetStatus: ConnectivityObserver.Status = ConnectivityObserver.Status.Lost,
    ): DetailedEpisode? {
        return characterClient.getEpisode(id)
    }
}