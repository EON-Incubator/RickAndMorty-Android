package com.example.rickandmorty.data.local

// import androidx.room.*
// import kotlinx.coroutines.flow.Flow
// import com.example.rickandmorty.data.local.schema.Location
//
// @Dao
// interface LocationDao {
//
//    @Query("SELECT * from locations ORDER BY name ASC")
//    fun getAllLocations(): Flow<List<Location>>
//
//    @Query("SELECT * from locations WHERE id = :id")
//    fun getLocation(id: Int): Flow<Location>
//
//    // Specify the conflict strategy as IGNORE, when the user tries to add an
//    // existing Item into the database Room ignores the conflict.
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(location: Location)
//
//    @Update
//    suspend fun update(location: Location)
//
//    @Delete
//    suspend fun delete(location: Location)
// }