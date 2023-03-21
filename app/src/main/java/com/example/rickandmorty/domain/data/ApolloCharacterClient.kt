package com.example.rickandmorty.domain.data

import com.apollographql.apollo3.ApolloClient
import com.example.*
import com.example.rickandmorty.domain.location.Location
import com.example.rickandmorty.domain.location.LocationDetail
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.domain.EpisodesData
import com.example.rickandmorty.domain.character.CharacterData
import com.example.type.FilterCharacter
import com.example.type.FilterEpisode
import com.example.type.FilterLocation

class ApolloCharacterClient(private val apolloClient: ApolloClient) :
    CharacterClient {
    override suspend fun getCharacters(filterCharacter: FilterCharacter, page: Int):
        CharacterData? {
        return apolloClient
            .query(CharactersQuery(filterCharacter, page))
            .execute()
            .data
            ?.characters
            ?.toCharacter()
    }

    override suspend fun getAllLocations(filterLocation: FilterLocation): List<Location> {
        return apolloClient
            .query(AllLocationsQuery(filterLocation))
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

    override suspend fun getEpisodes(filterEpisode: FilterEpisode, page: Int): EpisodesData? {
        return apolloClient
            .query(GetEpisodesQuery(filterEpisode, page))
            .execute()
            .data
            ?.episodes
            ?.toEpisodes()

//            ?.results
//            ?.mapNotNull { it?.toEpisodes() }
//            ?: emptyList()
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