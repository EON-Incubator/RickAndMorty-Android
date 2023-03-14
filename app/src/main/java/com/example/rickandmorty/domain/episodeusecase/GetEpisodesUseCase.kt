package com.example.rickandmorty.domain.episodeusecase

import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.Episodes

class GetEpisodesUseCase(private val characterClient: CharacterClient) {
    suspend fun execute(): List<Episodes> {
        return characterClient
            .getEpisodes()
            .sortedBy { it.name }
    }
}