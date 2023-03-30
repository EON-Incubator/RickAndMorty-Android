package com.example.rickandmorty.fakeRepo

import com.example.rickandmorty.domain.*
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.domain.character.CharacterData
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.domain.location.Location
import com.example.rickandmorty.domain.location.LocationData
import com.example.rickandmorty.domain.location.LocationDetail
import com.example.rickandmorty.domain.search.SearchResult
import com.example.type.FilterCharacter
import com.example.type.FilterEpisode
import com.example.type.FilterLocation

class FakeRepo : CharacterClient {

    private val episodes = mutableListOf<Episodes>()
    private val detailedEpisode = mutableListOf<DetailedEpisode>()

    private val locations = mutableListOf<Location>()
    override suspend fun getAllLocations(filterLocation: FilterLocation, page: Int): LocationData? {
        return LocationData(
            pages = Paginate(
                next = null,
                pages = 1,
                prev = null,
                count = 1
            ),
            locations = listOf(
                Location(
                    id = "id",
                    name = "name",
                    type = "type",
                    dimension = "dimension",
                    images = emptyList(),
                    created = "created"
                )
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

    override suspend fun getEpisodes(filterEpisode: FilterEpisode, page: Int): EpisodesData? {
        return EpisodesData(
            pages = null,
            episodesData = listOf(
                Episodes(
                    id = "id",
                    name = "name",
                    episode = "episode",
                    air_date = "air_date",
                    images = emptyList()
                )
            )
        )
    }

    override suspend fun getEpisode(id: String): DetailedEpisode? {
        if (id.equals("1")) {
            return DetailedEpisode(
                "id",
                "name1",
                "episode1",
                "airDate1",
                emptyList()
            )
        } else {
            return null
        }
    }

    override suspend fun getSearchResult(queryString: String, page: Int): SearchResult? {
        return SearchResult(
            CharacterData(
                pages = Paginate(
                    next = 1,
                    prev = 1,
                    pages = 2,
                    count = 1
                ),
                characters = listOf(
                    Character(
                        ID = "1",
                        name = "Rick",
                        image = "",
                        status = "Alive",
                        species = "Human",
                        gender = "Male"
                    )
                )
            ),
            LocationData(
                pages = Paginate(
                    next = null,
                    prev = 1,
                    pages = 2,
                    count = 1
                ),
                locations = listOf(
                    Location(
                        id = "1",
                        name = "Earth",
                        type = "Planet",
                        dimension = "Unknown",
                        images = emptyList(),
                        created = ""
                    )
                )
            ),
            LocationData(
                pages = Paginate(
                    next = 1,
                    prev = 1,
                    pages = 2,
                    count = 1
                ),
                locations = listOf(
                    Location(
                        id = "1",
                        name = "Earth",
                        type = "Planet",
                        dimension = "Unknown",
                        images = emptyList(),
                        created = ""
                    )
                )
            )
        )
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