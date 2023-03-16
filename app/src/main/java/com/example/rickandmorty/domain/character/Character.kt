package com.example.rickandmorty.domain.character

import com.example.rickandmorty.domain.Paginate

data class Character(
    val ID: String?,
    val name: String?,
    val image: String?,
    val species: String?,
    val status: String?,
    val gender: String?,
)

data class CharacterData(
    val pages: Paginate?,
    val characters: List<Character>?,
)