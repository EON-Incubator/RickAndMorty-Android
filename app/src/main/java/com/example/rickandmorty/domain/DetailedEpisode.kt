package com.example.rickandmorty.domain

data class DetailedEpisode(
    val name: String?,
    val episode: String?,
    val air_date: String?,
    val characters: List<String>
)
