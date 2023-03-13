package com.example.rickandmorty.domain.episodes

data class Episode(
    val epidoseid: String?,
    val name: String?,
    val images: List<String?>,
    val air_date: String?,
)