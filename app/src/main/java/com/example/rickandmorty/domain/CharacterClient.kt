package com.example.rickandmorty.domain

import com.example.rickandmorty.domain.character.CharacterData
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.domain.location.Location
import com.example.rickandmorty.domain.location.LocationDetail

interface CharacterClient {

    suspend fun getAllLocations(name: String = ""): List<Location>

    suspend fun getLocationDetail(id: String): LocationDetail?

    suspend fun getCharacters(name: String = "", page: Int = 1): CharacterData?

    suspend fun getSingleCharacter(code: String): DetailedCharacter?

    suspend fun getEpisodes(): List<Episodes>

    suspend fun getEpisode(id: String): DetailedEpisode?
}