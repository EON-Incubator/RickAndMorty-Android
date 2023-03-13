package com.example.rickandmorty.domain

import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.domain.location.Location
import com.example.rickandmorty.domain.location.LocationDetail

interface CharacterClient {
    suspend fun getCharacters(): List<Character>

    suspend fun getAllLocations(): List<Location>

    suspend fun getLocationDetail(id: String): LocationDetail?
}