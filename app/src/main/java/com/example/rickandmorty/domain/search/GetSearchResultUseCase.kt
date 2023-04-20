package com.example.rickandmorty.domain.search

import com.example.rickandmorty.data.local.repository.CharactersRepository
import com.example.rickandmorty.data.local.repository.EpisodesRepository
import com.example.rickandmorty.data.local.repository.LocationsRepository
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.domain.character.CharacterData
import com.example.rickandmorty.domain.episodes.Episodes
import com.example.rickandmorty.domain.episodes.EpisodesData
import com.example.rickandmorty.domain.location.Location
import com.example.rickandmorty.domain.location.LocationData
import com.example.rickandmorty.ui.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Class that gets the Data from CharacterClients
 * and perform functions such as
 * sorting and filtering
 **/
class GetSearchResultUseCase(
    private val characterClient: CharacterClient,
    private val charactersRepository: CharactersRepository,
    private val locationsRepository: LocationsRepository,
    private val episodesRepository: EpisodesRepository,
) {

    suspend fun execute(queryString: String, page: Int = 1): SearchResult? {
        if (DataState.isLocal) {
            var characters = charactersRepository.getAllCharactersStream(queryString)
            var locations = locationsRepository.getAllLocationsStream(queryString)
            var episodes = episodesRepository.getAllEpisodesStream(queryString)
            return SearchResult(
                characterData = CharacterData(
                    characters = characters.map {
                        it.map {
                            Character(
                                ID = it.ID,
                                name = it.name,
                                gender = it.gender,
                                species = it.species,
                                image = it.image,
                                status = it.status
                            )
                        }
                    }.stateIn(
                        scope = CoroutineScope(Dispatchers.IO)
                    ).value,
                    pages = null
                ),
                locationByName = LocationData(
                    locations = locations.map {
                        it.map {
                            Location(
                                id = it.id,
                                name = it.name,
                                images = it.images,
                                type = it.type,
                                created = "",
                                dimension = it.dimension
                            )
                        }
                    }.stateIn(
                        scope = CoroutineScope(Dispatchers.IO)
                    ).value,
                    pages = null
                ),
                locationByType = LocationData(
                    locations = locations.map {
                        it.map {
                            Location(
                                id = it.id,
                                name = it.name,
                                images = it.images,
                                type = it.type,
                                created = "",
                                dimension = it.dimension
                            )
                        }
                    }.stateIn(
                        scope = CoroutineScope(Dispatchers.IO)
                    ).value,
                    pages = null
                ),
                episodesData = EpisodesData(
                    episodesData = episodes.map {
                        it.map {
                            Episodes(
                                name = it.name,
                                images = it.images,
                                air_date = it.air_date,
                                id = it.id,
                                episode = it.episode
                            )
                        }
                    }.stateIn(
                        scope = CoroutineScope(Dispatchers.IO)
                    ).value,
                    pages = null
                )
            )
        }
        var resultData = characterClient.getSearchResult(queryString)
        return resultData?.copy(
            locationByName = resultData?.locationByName?.copy(
                locations = resultData.locationByName?.locations?.plus(
                    resultData?.locationByType?.locations
                        ?: emptyList()
                )?.distinct()
            )
        )
    }
}