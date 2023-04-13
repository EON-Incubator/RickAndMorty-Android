package com.example.rickandmorty.data.local.offlineRepository

import com.example.rickandmorty.data.local.EpisodeDao
import com.example.rickandmorty.data.local.repository.EpisodesRepository
import com.example.rickandmorty.data.local.schema.Episode
import kotlinx.coroutines.flow.Flow

class OfflineEpisodesRepository(private val episodeDao: EpisodeDao) : EpisodesRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    override fun getAllEpisodesStream(): Flow<List<Episode>> = episodeDao.getAllEpisodes()

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    override fun getEpisodeStream(id: Int): Flow<Episode?> = episodeDao.getEpisode(id)

    /**
     * Insert item in the data source
     */
    override suspend fun insertEpisode(episode: Episode) = episodeDao.insert(episode)

    /**
     * Delete item from the data source
     */
    override suspend fun deleteEpisode(episode: Episode) = episodeDao.delete(episode)

    /**
     * Update item in the data source
     */
    override suspend fun updateEpisode(episode: Episode) = episodeDao.update(episode)
}