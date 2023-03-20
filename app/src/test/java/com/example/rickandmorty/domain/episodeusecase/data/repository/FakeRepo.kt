package com.example.rickandmorty.domain.episodeusecase.data.repository

import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.Episodes
import com.example.rickandmorty.domain.character.CharacterData
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.domain.location.Location
import com.example.rickandmorty.domain.location.LocationDetail
import com.example.type.FilterCharacter
import com.example.type.FilterLocation

class FakeRepo : CharacterClient {

    private val episodes = mutableListOf<Episodes>()
    private val detailedEpisode = mutableListOf<DetailedEpisode>()

    private val locations = mutableListOf<Location>()

    override suspend fun getAllLocations(filterLocation: FilterLocation): List<Location> {
        return listOf(
            Location(
                id = "id",
                name = "name",
                type = "type",
                dimension = "dimension",
                images = emptyList(),
                created = "created"

            )
        )
    }

    override suspend fun getLocationDetail(id: String): LocationDetail? {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacters(
        filterCharacter: FilterCharacter,
        page: Int,
    ): CharacterData? {
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