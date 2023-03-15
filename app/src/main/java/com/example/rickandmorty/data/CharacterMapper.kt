package com.example.rickandmorty.data

import com.example.*
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.Episodes
import com.example.rickandmorty.domain.location.Location
import com.example.rickandmorty.domain.location.LocationDetail
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.domain.episodes.Episode

fun CharactersQuery.Result.toCharacter(): Character {
    return Character(
        ID = id,
        name = name,
        image = image,
        status = status,
        species = species,
        gender = gender
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
                dimension = "",
                created = ""
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
            Episode(
                it?.id,
                it?.name,
                it?.characters?.mapNotNull { it?.image } ?: emptyList(),
                it?.air_date
            )
        },
        dimension = location?.dimension,
        created = location?.created
    )
}

fun GetEpisodeQuery.Episode.toDetailedEpisode(): DetailedEpisode {
    return DetailedEpisode(
        id = id,
        name = name,
        episode = episode,
        air_date = air_date,
        characters = characters.mapNotNull { it?.name }
    )
}

fun GetEpisodesQuery.Result.toEpisodes(): Episodes {
    return Episodes(
        id = id,
        name = name,
        episode = episode,
        air_date = air_date,
        images = characters.mapNotNull { it?.image }
    )
}