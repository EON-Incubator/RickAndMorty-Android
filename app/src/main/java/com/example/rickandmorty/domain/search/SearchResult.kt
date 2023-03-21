package com.example.rickandmorty.domain.search

import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.domain.character.CharacterData
import com.example.rickandmorty.domain.location.Location

data class SearchResult(
    val characterData: CharacterData?,
    val locationByName: LocationData?,
    val locationByType: LocationData?,
)

data class LocationData(
    val pages: Paginate?,
    val locations: List<Location>?,
)