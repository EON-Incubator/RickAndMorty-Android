package com.example.rickandmorty.data

// class ApolloEpisodeClient(private val apolloClient: ApolloClient) : EpisodeClient {
//
//    override suspend fun getEpisodes(): List<Episodes> {
//        return apolloClient
//            .query(GetEpisodesQuery())
//            .execute()
//            .data
//            ?.episodes
//            ?.results
//            ?.mapNotNull { it?.toEpisodes() }
//            ?: emptyList()
//    }
//
//    override suspend fun getEpisode(id: String): DetailedEpisode? {
//        return apolloClient
//            .query(GetEpisodeQuery(id))
//            .execute()
//            .data
//            ?.episode
//            ?.toDetailedEpisode()
//    }
// }