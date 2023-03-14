package com.example.rickandmorty.domain.episodeusecase

import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.DetailedEpisode

class GetEpisodeUseCase(
    private val characterClient: CharacterClient,
) {
    suspend fun execute(id: String): DetailedEpisode? {
        return characterClient.getEpisode(id)
    }
}