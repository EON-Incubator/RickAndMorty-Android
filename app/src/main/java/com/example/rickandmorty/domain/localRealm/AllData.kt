package com.example.rickandmorty.domain.localRealm

import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.domain.character.DetailedCharacter

data class AllData(
    val characterData: CharacterData?,
    val locationData: LocationData?,
    val episodeData: EpisodesData?,
)

data class CharacterData(
    val pages: Paginate?,
    val characters: List<DetailedCharacter>?,
)

data class EpisodesData(
    val pages: Paginate?,
    val episodes: List<DetailedEpisode>?,
)

data class LocationData(
    val pages: Paginate?,
    val locations: List<LocationDetail>?,
)

data class LocationDetail(
    val id: String?,
    val dimension: String?,
    val name: String?,
    val residents: List<DetailedCharacter>?,
    val type: String?,
)

data class DetailedEpisode(
    val id: String?,
    val name: String?,
    val episode: String?,
    val air_date: String?,
    val characters: List<DetailedCharacter>,
)