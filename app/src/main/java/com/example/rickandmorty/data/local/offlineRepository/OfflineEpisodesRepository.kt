package com.example.rickandmorty.data.local.offlineRepository

import android.util.Log
import com.example.rickandmorty.data.local.repository.EpisodesRepository
import com.example.rickandmorty.data.local.schema.Episode
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineEpisodesRepository(private val realm: Realm) : EpisodesRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    override fun getAllEpisodesStream(): Flow<List<Episode>> {
        return realm.query<Episode>().asFlow().map { it.list }
    }

    override fun getAllEpisodeByPageNum(page: Int): Flow<List<Episode>> {
        return realm.query<Episode>().asFlow().map { it.list }
    }

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    override fun getEpisodeStream(id: Int): Episode? {
        return realm.query<Episode>(query = "id == $0", id.toString()).first().find()
    }

    /**
     * Insert item in the data source
     */
    override suspend fun insertEpisode(episode: Episode) {
        realm.write {
            if (query<Episode>(query = "id == $0", episode.id).first().find() != null) {
                var queriedCharacter =
                    query<Episode>(query = "id == $0", episode.id).first().find()
                queriedCharacter = episode
            } else {
                copyToRealm(episode)
            }
        }
    }

    /**
     * Delete item from the data source
     */
    override suspend fun deleteEpisode(episode: Episode) {
        realm.write {
            val person = query<Episode>(query = "id == $0", episode.id).first().find()
            try {
                person?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("MongoRepositoryImpl", "${e.message}")
            }
        }
    }

    /**
     * Update item in the data source
     */
    override suspend fun updateEpisode(episode: Episode) {
        realm.write {
            var queriedCharacter = query<Episode>(query = "ID == $0", episode.id).first().find()
            queriedCharacter = episode
        }
    }
}