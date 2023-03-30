package com.example.rickandmorty.domain.episodes

import com.example.rickandmorty.domain.Paginate

data class Episodes(
    val id: String?,
    val name: String?,
    val episode: String?,
    val air_date: String?,
    val images: List<String>?,
)

data class EpisodesData(
    val pages: Paginate?,
    val episodesData: List<Episodes>?,
)