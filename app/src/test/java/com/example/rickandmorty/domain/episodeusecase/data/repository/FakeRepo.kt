package com.example.rickandmorty.domain.episodeusecase.data.repository

import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.Episodes
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.domain.location.Location
import com.example.rickandmorty.domain.location.LocationDetail

class FakeRepo : CharacterClient {

    private val episodes = mutableListOf<Episodes>()
    private val detailedEpisode = mutableListOf<DetailedEpisode>()

    override suspend fun getAllLocations(name: String): List<Location> {
        TODO("Not yet implemented")
    }

    override suspend fun getLocationDetail(id: String): LocationDetail? {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacters(name: String): List<Character> {
        TODO("Not yet implemented")
    }

    override suspend fun getSingleCharacter(code: String): DetailedCharacter? {
        TODO("Not yet implemented")
    }

    override suspend fun getEpisodes(): List<Episodes> {
        return FakeDataSource.episodesList
    }

    override suspend fun getEpisode(id: String): DetailedEpisode? {
        TODO("Not yet implemented")
    }
}