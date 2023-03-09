package com.example.rickandmorty.data

import com.example.GetEpisodeQuery
import com.example.GetEpisodesQuery
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.Episodes

fun GetEpisodeQuery.Episode.toDetailedEpisode(): DetailedEpisode{
    return DetailedEpisode(
        name = name,
        episode = episode,
        air_date = air_date,
        characters = characters.mapNotNull { it?.name },
    )
}

fun GetEpisodesQuery.Result.toEpisodes(): Episodes{
    return Episodes(
        name = name,
        episode = episode,
        air_date = air_date
    )
}