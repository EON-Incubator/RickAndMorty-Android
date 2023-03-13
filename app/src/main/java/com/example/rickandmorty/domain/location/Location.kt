package com.example.rickandmorty.domain.location

data class Location(
    val id: String?,
    val name: String?,
    val type: String?,
    val dimension: String?,
    val images: List<String>?,
)