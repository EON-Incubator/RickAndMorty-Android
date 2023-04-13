package com.example.rickandmorty.data.local.repository

import com.example.rickandmorty.data.local.schema.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllEpisodesStream(): Flow<List<Episode>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getEpisodeStream(id: Int): Flow<Episode?>

    /**
     * Insert item in the data source
     */
    suspend fun insertEpisode(item: Episode)

    /**
     * Delete item from the data source
     */
    suspend fun deleteEpisode(item: Episode)

    /**
     * Update item in the data source
     */
    suspend fun updateEpisode(item: Episode)
}