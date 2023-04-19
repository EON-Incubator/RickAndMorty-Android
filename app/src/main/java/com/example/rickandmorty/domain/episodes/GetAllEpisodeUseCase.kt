package com.example.rickandmorty.domain.episodes

import androidx.compose.runtime.mutableStateOf
import com.example.rickandmorty.data.local.repository.EpisodesRepository
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.network.ConnectivityObserver
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
        internetStatus: ConnectivityObserver.Status = ConnectivityObserver.Status.Lost,
    ): EpisodesData? {
        var episodeData: EpisodesData? = null
        if (internetStatus == ConnectivityObserver.Status.Available) {
            episodeData = characterClient.getEpisodes(filterEpisode, page)
        } else {
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
                count = 20,
                prev = null,
                next = null
            )

            episodeData = EpisodesData(
                pages = pagesOffline,
                episodesData = episodeOffline
            )
//            episodeOffline.forEach {
//                Log.v("Page: $page", it.name.toString())
//            }
        }

        return episodeData ?: null

//        return EpisodesData(
//            episodesData = episodesData?.episodesData
//                ?.sortedBy { it.episode },
//            pages = episodesData?.pages
//        )
    }
}