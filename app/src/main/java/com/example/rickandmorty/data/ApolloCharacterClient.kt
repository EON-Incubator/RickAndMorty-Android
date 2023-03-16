package com.example.rickandmorty.data

import com.apollographql.apollo3.ApolloClient
import com.example.*
import com.example.rickandmorty.domain.location.Location
import com.example.rickandmorty.domain.location.LocationDetail
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.domain.Episodes
import com.example.rickandmorty.domain.character.CharacterData

class ApolloCharacterClient(private val apolloClient: ApolloClient) : CharacterClient {
    override suspend fun getCharacters(name: String, page: Int): CharacterData? {
        return apolloClient
            .query(CharactersQuery(name, page))
            .execute()
            .data
            ?.characters
            ?.toCharacter()
    }

    override suspend fun getAllLocations(name: String): List<Location> {
        return apolloClient
            .query(AllLocationsQuery(name))
            .execute()
            .data
            ?.locations
            ?.results
            ?.mapNotNull { it?.toAllLocations() }
            ?: emptyList<Location>()
    }

    override suspend fun getLocationDetail(id: String): LocationDetail? {
        return apolloClient
            .query(LocationDetailQuery(id))
            .execute()
            .data
            ?.location
            ?.toLocationDetail()
    }

    override suspend fun getSingleCharacter(code: String): DetailedCharacter? {
        return apolloClient.query(SpecificCharacterQuery(code))
            .execute().data?.character?.toSpecificChar()
    }

    override suspend fun getEpisodes(): List<Episodes> {
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