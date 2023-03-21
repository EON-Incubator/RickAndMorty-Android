package com.example.rickandmorty.data

import com.example.*
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.Episodes
import com.example.rickandmorty.domain.EpisodesData
import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.domain.location.Location
import com.example.rickandmorty.domain.location.LocationDetail
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.domain.character.CharacterData
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.domain.episodes.Episode
import com.example.rickandmorty.domain.search.LocationData
import com.example.rickandmorty.domain.search.SearchResult

fun CharactersQuery.Characters.toCharacter(): CharacterData {
    return CharacterData(
        pages = Paginate(
            next = info?.pageInfo?.next,
            prev = info?.pageInfo?.prev,
            pages = info?.pageInfo?.pages,
            count = info?.pageInfo?.count
        ),
        characters = results?.mapNotNull {
            Character(
                ID = it?.id,
                name = it?.name,
                image = it?.image,
                status = it?.status,
                species = it?.species,
                gender = it?.gender
            )
        }
    )
}

fun AllLocationsQuery.Result.toAllLocations(): Location {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension,
        images = residents.mapNotNull { it?.image },
        created = ""
    )
}

fun LocationDetailQuery.Location.toLocationDetail(): LocationDetail {
    return LocationDetail(
        dimension = dimension,
        name = name,
        type = type,
        residents = residents.mapNotNull {
            DetailedCharacter(
                it?.id,
                it?.name,
                it?.image,
                it?.species,
                it?.status,
                it?.gender,
                episode = emptyList(),
                lastseen = "",
                origin = "",
                lastseenId = "",
                originId = ""
            )
        }
    )
}

fun SpecificCharacterQuery.Character.toSpecificChar(): DetailedCharacter {
    return DetailedCharacter(
        ID = id,
        name = name,
        image = image,
        status = status,
        species = species,
        gender = gender,
        episode = episode.mapNotNull {
            Episodes(
                it?.id,
                it?.name,
                it?.episode,
                it?.air_date,
                it?.characters?.mapNotNull { it?.image } ?: emptyList()
            )
        },

        lastseen = location?.name.toString(),
        origin = origin?.name.toString(),
        lastseenId = location?.id.toString(),
        originId = origin?.id.toString()
    )
}

fun GetEpisodeQuery.Episode.toDetailedEpisode(): DetailedEpisode {
    return DetailedEpisode(
        id = id,
        name = name,
        episode = episode,
        air_date = air_date,
        characters = characters.mapNotNull {
            Character(
                it?.id,
                it?.name,
                it?.image,
                it?.species,
                it?.status,
                it?.gender
            )
        }
    )
}

fun GetEpisodesQuery.Episodes.toEpisodes(): EpisodesData {
    return EpisodesData(
        pages = Paginate(
            next = info?.pageInfo?.next,
            prev = info?.pageInfo?.prev,
            pages = info?.pageInfo?.pages,
            count = info?.pageInfo?.count
        ),
        episodesData = results?.mapNotNull {
            Episodes(
                it?.id,
                it?.name,
                it?.episode,
                it?.air_date,
                it?.characters?.mapNotNull { it?.image }
            )
        }

    )
}

fun SearchQuery.Data.toSearchResult(): SearchResult? {
    return SearchResult(
        CharacterData(
            pages = Paginate(
                next = characters?.info?.pageInfo?.next,
                prev = characters?.info?.pageInfo?.prev,
                pages = characters?.info?.pageInfo?.pages,
                count = characters?.info?.pageInfo?.count
            ),
            characters = characters?.results?.mapNotNull {
                Character(
                    ID = it?.id,
                    name = it?.name,
                    image = it?.image,
                    status = it?.status,
                    species = it?.species,
                    gender = it?.gender
                )
            }
        ),
        LocationData(
            pages = Paginate(
                next = locationsByName?.info?.pageInfo?.next,
                prev = locationsByName?.info?.pageInfo?.prev,
                pages = locationsByName?.info?.pageInfo?.pages,
                count = locationsByName?.info?.pageInfo?.count
            ),
            locations = locationsByName?.results?.mapNotNull {
                Location(
                    id = it?.id,
                    name = it?.name,
                    type = it?.type,
                    dimension = it?.dimension,
                    images = it?.residents?.mapNotNull { it?.image },
                    created = ""
                )
            }
        ),
        LocationData(
            pages = Paginate(
                next = locationsByType?.info?.pageInfo?.next,
                prev = locationsByType?.info?.pageInfo?.prev,
                pages = locationsByType?.info?.pageInfo?.pages,
                count = locationsByType?.info?.pageInfo?.count
            ),
            locations = locationsByType?.results?.mapNotNull {
                Location(
                    id = it?.id,
                    name = it?.name,
                    type = it?.type,
                    dimension = it?.dimension,
                    images = it?.residents?.mapNotNull { it?.image },
                    created = ""
                )
            }
        )
    )
}