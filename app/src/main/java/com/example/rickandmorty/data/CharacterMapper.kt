package com.example.rickandmorty.data

import com.example.CharactersQuery
import com.example.SpecificCharacterQuery
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.domain.episodes.Episode

fun CharactersQuery.Result.toCharacter(): Character {
    return Character(
        ID = id,
        name = name,
        image = image,
        status = status,
        species = species
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
        }

    )
}