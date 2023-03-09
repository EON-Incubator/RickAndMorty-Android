package com.example.rickandmorty.domain.episodeusecase

import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.EpisodeClient

class GetEpisodeUseCase(
    private val episodeClient: EpisodeClient,
) {
    suspend fun execute(id: String): DetailedEpisode? {
        return episodeClient.getEpisode(id)
    }
}