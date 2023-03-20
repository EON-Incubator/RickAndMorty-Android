package com.example.rickandmorty.domain.episodeusecase

import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.Episodes
import com.example.rickandmorty.domain.EpisodesData
import com.example.rickandmorty.domain.character.CharacterData
import com.example.type.FilterCharacter
import com.example.type.FilterEpisode

class GetAllEpisodeUseCase(
    private val characterClient: CharacterClient,
) {
    suspend fun execute(): List<Episodes> {
        return characterClient
            .getEpisodes()
            ?.episodesData
            ?.sortedBy { it.name }?: emptyList()
    }

    suspend fun sortEpisodeById(filterEpisode: FilterEpisode = FilterEpisode(), page: Int = 1): EpisodesData {
        val episodesData = characterClient.getEpisodes(filterEpisode, page)
        return EpisodesData(
            episodesData = episodesData?.episodesData
                ?.sortedBy { it.id },
            pages = episodesData?.pages
        )
    }
}