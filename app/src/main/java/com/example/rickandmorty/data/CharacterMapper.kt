package com.example.rickandmorty.data

import com.example.AllLocationsQuery
import com.example.CharactersQuery
import com.example.LocationDetailQuery
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.domain.location.Location
import com.example.rickandmorty.domain.location.LocationDetail

fun CharactersQuery.Result.toCharacter(): Character {
    return Character(
        ID = id,
        name = name,
        image = image,
        status = status,
        species = species
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
        type = type
    )
}