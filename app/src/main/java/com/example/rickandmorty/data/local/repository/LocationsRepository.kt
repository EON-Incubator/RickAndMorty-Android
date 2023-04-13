package com.example.rickandmorty.data.local.repository

import com.example.rickandmorty.data.local.schema.Location
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllLocationsStream(): Flow<List<Location>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getLocationStream(id: Int): Flow<Location?>

    /**
     * Insert item in the data source
     */
    suspend fun insertLocation(item: Location)

    /**
     * Delete item from the data source
     */
    suspend fun deleteLocation(item: Location)

    /**
     * Update item in the data source
     */
    suspend fun updateLocation(item: Location)
}