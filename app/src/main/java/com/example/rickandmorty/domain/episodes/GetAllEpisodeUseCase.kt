package com.example.rickandmorty.domain.episodes

import com.example.rickandmorty.domain.CharacterClient
import com.example.type.FilterEpisode

class GetAllEpisodeUseCase(
    private val characterClient: CharacterClient,
) {
    suspend fun sortEpisodeById(
        filterEpisode: FilterEpisode = FilterEpisode(),
        page: Int = 1,
    ): EpisodesData {
        val episodesData = characterClient.getEpisodes(filterEpisode, page)
        return EpisodesData(
            episodesData = episodesData?.episodesData
                ?.sortedBy { it.episode },
            pages = episodesData?.pages
        )
    }
}