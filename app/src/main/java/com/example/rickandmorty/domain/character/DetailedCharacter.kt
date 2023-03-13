package com.example.rickandmorty.domain.character

import com.example.rickandmorty.domain.episodes.Episode

data class DetailedCharacter(

    val ID: String?,
    val name: String?,
    val image: String?,
    val species: String?,
    val status: String?,
    val gender: String?,
    val episode: List<Episode>, // val locations: List<Any>
)