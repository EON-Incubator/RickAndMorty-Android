package com.example.rickandmorty.domain.episodeusecase

import com.example.rickandmorty.domain.EpisodeClient
import com.example.rickandmorty.domain.Episodes

class GetEpisodesUseCase(
    private val episodeClient: EpisodeClient
) {
    suspend fun execute(page:Int): List<Episodes>{
        return episodeClient
            .getEpisodes(page)
            .sortedBy { it.name }
    }
}