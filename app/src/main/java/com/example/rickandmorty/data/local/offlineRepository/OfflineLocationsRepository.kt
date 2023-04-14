package com.example.rickandmorty.data.local.offlineRepository

import android.util.Log
import com.example.rickandmorty.data.local.repository.LocationsRepository
import com.example.rickandmorty.data.local.schema.Location
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineLocationsRepository(private val realm: Realm) : LocationsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    override fun getAllLocationsStream(): Flow<List<Location>> {
        return realm.query<Location>().asFlow().map { it.list }
    }

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    override fun getLocationStream(id: Int): Location? {
        return realm.query<Location>(query = "id == $0", id).first().find()
    }

    /**
     * Insert item in the data source
     */
    override suspend fun insertLocation(location: Location) {
        realm.write {
            if (query<Location>(query = "id == $0", location.id).first().find() != null) {
                var queriedCharacter =
                    query<Location>(query = "id == $0", location.id).first().find()
                queriedCharacter = location
            } else {
                copyToRealm(location)
            }
        }
    }

    /**
     * Delete item from the data source
     */
    override suspend fun deleteLocation(location: Location) {
        realm.write {
            val person = query<Location>(query = "id == $0", location.id).first().find()
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
    override suspend fun updateLocation(location: Location) {
        realm.write {
            var queriedCharacter = query<Location>(query = "ID == $0", location.id).first().find()
            queriedCharacter = location
        }
    }
}