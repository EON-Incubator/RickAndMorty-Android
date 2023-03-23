package com.example.rickandmorty.domain.episodeusecase.data.repository

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
            pages = null,
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

    override suspend fun getCharacters(
        filterCharacter: FilterCharacter,
        page: Int,
    ): CharacterData? {
        TODO("Not yet implemented")
    }

    override suspend fun getSingleCharacter(code: String): DetailedCharacter? {
        TODO("Not yet implemented")
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
//        if(queryString.equals( "Rick")){
        return SearchResult(
            CharacterData(
                pages = Paginate(
                    next = null,
                    prev = 1,
                    pages = 1,
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
                    pages = 1,
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
                    next = null,
                    prev = 1,
                    pages = 1,
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
//        else{
//            return null
//        }
//    }
}