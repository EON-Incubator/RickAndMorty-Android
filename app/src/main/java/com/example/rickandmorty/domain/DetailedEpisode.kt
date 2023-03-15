package com.example.rickandmorty.domain

data class DetailedEpisode(
    val id: String?,
    val name: String?,
    val episode: String?,
    val air_date: String?,
    val characters: List<String>,
)