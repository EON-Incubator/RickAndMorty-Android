package com.example.rickandmorty.domain.location

import com.example.rickandmorty.domain.Paginate

data class Location(
    val id: String?,
    val name: String?,
    val type: String?,
    val dimension: String?,
    val images: List<String>?,
    val created: String,
)

data class LocationData(
    val pages: Paginate?,
    val locations: List<Location>?,
)