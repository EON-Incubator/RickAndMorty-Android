package com.example.rickandmorty.data.local.repository

import com.example.rickandmorty.data.local.schema.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllEpisodesStream(queryString: String = ""): Flow<List<Episode>>

    fun getAllEpisodeByPageNum(page: Int = 1): Flow<List<Episode>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getEpisodeStream(id: Int): Episode?

    /**
     * Insert item in the data source
     */
    suspend fun insertEpisode(episode: Episode)

    /**
     * Delete item from the data source
     */
    suspend fun deleteEpisode(episode: Episode)

    /**
     * Update item in the data source
     */
    suspend fun updateEpisode(episode: Episode)
}