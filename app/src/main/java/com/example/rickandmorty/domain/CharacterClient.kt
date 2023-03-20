package com.example.rickandmorty.domain

import com.example.rickandmorty.domain.character.CharacterData
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.domain.location.Location
import com.example.rickandmorty.domain.location.LocationDetail
import com.example.type.FilterCharacter
import com.example.type.FilterEpisode
import com.example.type.FilterLocation

interface CharacterClient {

    suspend fun getAllLocations(filterLocation: FilterLocation = FilterLocation()): List<Location>

    suspend fun getLocationDetail(id: String): LocationDetail?

    suspend fun getCharacters(filterCharacter: FilterCharacter = FilterCharacter(), page: Int = 1): CharacterData?

    suspend fun getSingleCharacter(code: String): DetailedCharacter?

    suspend fun getEpisodes(filterEpisodes: FilterEpisode = FilterEpisode(), page: Int = 1): EpisodesData?

    suspend fun getEpisode(id: String): DetailedEpisode?
}