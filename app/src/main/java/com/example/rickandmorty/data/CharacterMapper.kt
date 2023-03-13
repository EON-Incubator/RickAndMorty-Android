package com.example.rickandmorty.data

import com.example.CharactersQuery
import com.example.SpecificCharacterQuery
import com.example.rickandmorty.domain.character.Character

fun CharactersQuery.Result.toCharacter(): Character {
    return Character(
        ID = id,
        name = name,
        image = image,
        status = status,
        species = species
    )
}

fun SpecificCharacterQuery.Character.toSpecificChar(): com.example.rickandmorty.domain.character.Character {
    return Character(
        ID = id,
        name = name,
        image = image,
        status = status,
        species = species
    )
}