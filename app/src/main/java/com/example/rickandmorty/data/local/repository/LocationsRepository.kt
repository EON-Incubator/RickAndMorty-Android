package com.example.rickandmorty.data.local.repository

import com.example.rickandmorty.data.local.schema.Location
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllLocationsStream(queryString: String = ""): Flow<List<Location>>

    fun getAllLocationByPageNum(page: Int = 1): Flow<List<Location>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getLocationStream(id: Int): Location?

    /**
     * Insert item in the data source
     */
    suspend fun insertLocation(location: Location)

    /**
     * Delete item from the data source
     */
    suspend fun deleteLocation(location: Location)

    /**
     * Update item in the data source
     */
    suspend fun updateLocation(location: Location)
}