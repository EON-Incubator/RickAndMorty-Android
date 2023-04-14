package com.example.rickandmorty.domain

import com.example.rickandmorty.domain.character.CharacterData
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.domain.episodes.DetailedEpisode
import com.example.rickandmorty.domain.episodes.EpisodesData
import com.example.rickandmorty.domain.localRealm.AllData
import com.example.rickandmorty.domain.location.LocationData
import com.example.rickandmorty.domain.location.LocationDetail
import com.example.rickandmorty.domain.search.SearchResult
import com.example.type.FilterCharacter
import com.example.type.FilterEpisode
import com.example.type.FilterLocation

interface CharacterClient {

    suspend fun getAllLocations(
        filterLocation: FilterLocation = FilterLocation(),
        page: Int = 1,
    ): LocationData?

    suspend fun getLocationDetail(id: String): LocationDetail?

    suspend fun getCharacters(
        filterCharacter: FilterCharacter = FilterCharacter(),
        page: Int = 1,
    ): CharacterData?

    suspend fun getSingleCharacter(code: String): DetailedCharacter?

    suspend fun getEpisodes(
        filterEpisode: FilterEpisode = FilterEpisode(),
        page: Int = 1,
    ): EpisodesData?

    suspend fun getEpisode(id: String): DetailedEpisode?

    suspend fun getSearchResult(queryString: String, page: Int = 1): SearchResult?

    suspend fun getAllData(page: Int = 1): AllData?
}