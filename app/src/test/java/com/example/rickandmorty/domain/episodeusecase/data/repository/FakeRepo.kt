package com.example.rickandmorty.domain.episodeusecase.data.repository

import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.Episodes
import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.domain.character.Character
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
        if (id.equals("1")) {
            return LocationDetail(
                "dimension1",
                "name1",
                emptyList(),
                "type1"
            )
        } else {
            return null
        }
    }

    override suspend fun getEpisodes(): List<Episodes> {
        return FakeDataSource.episodesList
    }

    override suspend fun getEpisode(id: String): DetailedEpisode? {
        TODO("Not yet implemented")
    }

    override suspend fun getSingleCharacter(code: String): DetailedCharacter? {
        if (code.equals("1")) {
            return DetailedCharacter(
                "ID",
                "name1",
                "img1",
                "species",
                "status",
                "gender1",
                emptyList<Episodes>(),
                "location1",
                "loci1ID",
                "origin1",
                "origin1ID"
            )
        } else {
            return null
        }
    }

    override suspend fun getCharacters(
        filterCharacter: FilterCharacter,
        page: Int,
    ): CharacterData? {
        return CharacterData(
            pages = Paginate(

                3,
                10,
                1,
                20
            ),
            characters = listOf(
                Character(
                    "ID",
                    "name2",
                    "img2",
                    "species2",
                    "status1",
                    "gender"
                )

            )

        )
    }
}