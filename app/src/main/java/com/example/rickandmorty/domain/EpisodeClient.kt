package com.example.rickandmorty.domain

interface EpisodeClient {
    suspend fun getEpisodes(page: Int): List<Episodes>
    suspend fun getEpisode(id: String): DetailedEpisode?
}