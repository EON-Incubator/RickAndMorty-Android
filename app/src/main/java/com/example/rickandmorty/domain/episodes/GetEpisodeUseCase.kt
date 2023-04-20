package com.example.rickandmorty.domain.episodes

import android.util.Log
import com.example.rickandmorty.data.local.repository.CharactersRepository
import com.example.rickandmorty.data.local.repository.EpisodesRepository
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.network.ConnectivityObserver

class GetEpisodeUseCase(
    private val characterClient: CharacterClient,
    private val episodesRepository: EpisodesRepository,
    private val charactersRepository: CharactersRepository,
) {
    suspend fun execute(
        id: String,
        internetStatus: ConnectivityObserver.Status = ConnectivityObserver.Status.Lost,
    ): DetailedEpisode? {
        if (internetStatus == ConnectivityObserver.Status.Available) {
            return characterClient.getEpisode(id)
        } else {
            val episodeRealm = episodesRepository.getEpisodeStream(id.toInt())
            Log.v("Episode Realm", episodeRealm?.id.toString())
            var id: List<Int> = episodeRealm?.characters?.map {
                it.toInt()
            } ?: emptyList()

            var residents = id.map {
                charactersRepository.getCharacterStream(it)
            }.map {
                com.example.rickandmorty.domain.character.Character(
                    name = it?.name,
                    gender = it?.gender,
                    species = it?.species,
                    status = it?.status,
                    image = it?.image,
                    ID = it?.ID
                )
            }
//            var residents = id.forEach {
//                var eachResi=charactersRepository.getCharacterStream(it.toInt())
//
//            }

//            id.forEach {
//                Log.v("Strings", id.toString())
//            }
            val detailedEpisode = DetailedEpisode(
                episode = episodeRealm?.episode,
                name = episodeRealm?.name,
                characters = residents,
                id = episodeRealm?.id,
                air_date = episodeRealm?.air_date,
                images = emptyList(),
                desc = episodeRealm?.overview ?: "",
                ratings = 3,
                videoLink = ""

            )

            return detailedEpisode
        }
    }
}