package com.example.rickandmorty.domain.episodeusecase

import com.example.rickandmorty.domain.EpisodeClient
import com.example.rickandmorty.domain.Episodes

class GetEpisodesUseCase(private val episodeClient: EpisodeClient) {
    suspend fun execute(): List<Episodes> {
        return episodeClient
            .getEpisodes()
            .sortedBy { it.name }
    }
}