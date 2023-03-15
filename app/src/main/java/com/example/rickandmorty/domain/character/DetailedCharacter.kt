package com.example.rickandmorty.domain.character

import com.example.rickandmorty.domain.Episodes
data class DetailedCharacter(
    val ID: String?,
    val name: String?,
    val image: String?,
    val species: String?,
    val status: String?,
    val gender: String?,
    val episode: List<Episodes>,
    val dimension: String?,
    val created: String?,
)