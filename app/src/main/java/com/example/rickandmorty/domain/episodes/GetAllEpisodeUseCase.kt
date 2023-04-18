package com.example.rickandmorty.domain.episodes

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.rickandmorty.data.local.repository.EpisodesRepository
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.Paginate
import com.example.type.FilterEpisode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class GetAllEpisodeUseCase @Inject constructor(
    private val characterClient: CharacterClient,
    private val episodesRepository: EpisodesRepository,
) {
    suspend fun sortEpisodeById(
        filterEpisode: FilterEpisode = FilterEpisode(),
        page: Int = 1,
    ): EpisodesData {
        val episodesData = characterClient.getEpisodes(filterEpisode, page)

        var data = mutableStateOf(emptyList<com.example.rickandmorty.data.local.schema.Episode>())

        episodesRepository.getAllEpisodeByPageNum(page).map {
            data.value = it
        }.stateIn(
            scope = CoroutineScope(Dispatchers.IO)
        )

        var episodeOffline: List<Episodes> =
            data.value.map { episode ->
                Episodes(
                    name = episode.name,
                    images = episode.images,
                    air_date = episode.air_date,
                    episode = episode.episode,
                    id = episode.id
                )
            }

        var pagesOffline = Paginate(
            pages = 1,
            count = 2,
            prev = when (page - 1) {
                0 -> null
                else -> (page - 1)
            },
            next = when (page + 1) {
                0 -> null
                else -> (page - 1)
            }
        )
        episodeOffline.forEach {
            Log.v("Page: $page", it.name.toString())
        }

        return EpisodesData(
            episodesData = episodeOffline,
            pages = pagesOffline
        )

//        return EpisodesData(
//            episodesData = episodesData?.episodesData
//                ?.sortedBy { it.episode },
//            pages = episodesData?.pages
//        )
    }
}