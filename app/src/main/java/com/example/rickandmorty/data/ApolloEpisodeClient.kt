package com.example.rickandmorty.data

import com.apollographql.apollo3.ApolloClient
import com.example.GetEpisodeQuery
import com.example.GetEpisodesQuery
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.EpisodeClient
import com.example.rickandmorty.domain.Episodes

class ApolloEpisodeClient(private val apolloClient: ApolloClient) : EpisodeClient {

    override suspend fun getEpisodes(page: Int): List<Episodes> {
        return apolloClient
            .query(GetEpisodesQuery())
            .execute()
            .data
            ?.episodes
            ?.results
            ?.mapNotNull { it?.toEpisodes() }
            ?: emptyList()
    }

    override suspend fun getEpisode(id: String): DetailedEpisode? {
        return apolloClient
            .query(GetEpisodeQuery(id))
            .execute()
            .data
            ?.episode
            ?.toDetailedEpisode()
    }


}