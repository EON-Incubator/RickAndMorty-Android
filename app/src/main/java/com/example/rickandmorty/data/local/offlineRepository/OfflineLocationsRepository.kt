package com.example.rickandmorty.data.local.offlineRepository

import com.example.rickandmorty.data.local.LocationDao
import com.example.rickandmorty.data.local.repository.LocationsRepository
import com.example.rickandmorty.data.local.schema.Location
import kotlinx.coroutines.flow.Flow

class OfflineLocationsRepository(private val locationDao: LocationDao) : LocationsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    override fun getAllLocationsStream(): Flow<List<Location>> = locationDao.getAllLocations()

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    override fun getLocationStream(id: Int): Flow<Location?> = locationDao.getLocation(id)

    /**
     * Insert item in the data source
     */
    override suspend fun insertLocation(location: Location) = locationDao.insert(location)

    /**
     * Delete item from the data source
     */
    override suspend fun deleteLocation(location: Location) = locationDao.delete(location)

    /**
     * Update item in the data source
     */
    override suspend fun updateLocation(location: Location) = locationDao.update(location)
}