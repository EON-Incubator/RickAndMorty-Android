package com.example.rickandmorty.domain.location

import com.example.rickandmorty.domain.character.DetailedCharacter

data class LocationDetail(
    val dimension: String?,
    val name: String?,
    val residents: List<DetailedCharacter>?,
    val type: String?,
)