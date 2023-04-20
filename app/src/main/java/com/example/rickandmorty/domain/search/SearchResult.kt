package com.example.rickandmorty.domain.search

import com.example.rickandmorty.domain.character.CharacterData
import com.example.rickandmorty.domain.episodes.EpisodesData
import com.example.rickandmorty.domain.location.LocationData

data class SearchResult(
    val characterData: CharacterData?,
    val locationByName: LocationData?,
    val locationByType: LocationData?,
    val episodesData: EpisodesData? = null,
)