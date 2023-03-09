package com.example.rickandmorty.data

import com.example.CharactersQuery
import com.example.rickandmorty.domain.Character

fun CharactersQuery.Result.toCharacter(): Character {
    return Character(
        ID = id,
        name = name,
        image = image,
        status = status,
        species = species
    )
}