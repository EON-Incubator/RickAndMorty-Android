package com.example.rickandmorty.domain

import com.example.rickandmorty.domain.character.Character

data class DetailedEpisode(
    val id: String?,
    val name: String?,
    val episode: String?,
    val air_date: String?,
    val characters: List<Character>,
)