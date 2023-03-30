package com.example.rickandmorty.data

import com.apollographql.apollo3.ApolloClient
import com.example.*
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.character.CharacterData
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.domain.episodes.DetailedEpisode
import com.example.rickandmorty.domain.episodes.EpisodesData
import com.example.rickandmorty.domain.location.LocationData
import com.example.rickandmorty.domain.location.LocationDetail
import com.example.rickandmorty.domain.search.SearchResult
import com.example.type.FilterCharacter
import com.example.type.FilterEpisode
import com.example.type.FilterLocation

class ApolloCharacterClient(private val apolloClient: ApolloClient) :
    CharacterClient {
    override suspend fun getCharacters(filterCharacter: FilterCharacter, page: Int):
        CharacterData? {
        return apolloClient
            .query(GetAllCharactersQuery(filterCharacter, page))
            .execute()
            .data
            ?.characters
            ?.toCharacter()
    }

    override suspend fun getAllLocations(filterLocation: FilterLocation, page: Int): LocationData? {
        return apolloClient
            .query(AllLocationsQuery(filterLocation, page))
            .execute()
            .data
            ?.locations
            ?.toAllLocations()
    }

    override suspend fun getLocationDetail(id: String): LocationDetail? {
        return apolloClient
            .query(GetLocationByIdQuery(id))
            .execute()
            .data
            ?.location
            ?.toLocationDetail()
    }

    override suspend fun getSingleCharacter(code: String): DetailedCharacter? {
        return apolloClient.query(GetCharacterByIdQuery(code))
            .execute().data?.character?.toSpecificChar()
    }

    override suspend fun getEpisodes(filterEpisode: FilterEpisode, page: Int): EpisodesData? {
        return apolloClient
            .query(GetEpisodesQuery(filterEpisode, page))
            .execute()
            .data
            ?.episodes
            ?.toEpisodes()
    }

    override suspend fun getEpisode(id: String): DetailedEpisode? {
        return apolloClient
            .query(GetEpisodeByIdQuery(id))
            .execute()
            .data
            ?.episode
            ?.toDetailedEpisode()
    }

    override suspend fun getSearchResult(queryString: String, page: Int): SearchResult? {
        return apolloClient
            .query(SearchQuery(queryString, page))
            .execute()
            .data
            ?.toSearchResult()
    }
}