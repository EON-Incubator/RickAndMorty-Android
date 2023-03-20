package com.example.rickandmorty.domain.data

import com.example.*
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.Episodes
import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.domain.location.Location
import com.example.rickandmorty.domain.location.LocationDetail
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.domain.character.CharacterData
import com.example.rickandmorty.domain.character.DetailedCharacter

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

fun GetEpisodesQuery.Result.toEpisodes(): Episodes {
    return Episodes(
        id = id,
        name = name,
        episode = episode,
        air_date = air_date,
        images = characters.mapNotNull { it?.image }
    )
}