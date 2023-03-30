package com.example.rickandmorty.domain.episodes

import com.example.rickandmorty.domain.CharacterClient

class GetEpisodeUseCase(
    private val characterClient: CharacterClient,
) {
    suspend fun execute(id: String): DetailedEpisode? {
        return characterClient.getEpisode(id)
    }
}