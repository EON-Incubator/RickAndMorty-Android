package com.example.rickandmorty.data.local

// import androidx.room.*
// import com.example.rickandmorty.data.local.schema.Episode
// import kotlinx.coroutines.flow.Flow

// @Dao
// interface EpisodeDao {
//
//    @Query("SELECT * from episodes ORDER BY name ASC")
//    fun getAllEpisodes(): Flow<List<Episode>>
//
//    @Query("SELECT * from episodes WHERE id = :id")
//    fun getEpisode(id: Int): Flow<Episode>
//
//    // Specify the conflict strategy as IGNORE, when the user tries to add an
//    // existing Item into the database Room ignores the conflict.
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(episode: Episode)
//
//    @Update
//    suspend fun update(episode: Episode)
//
//    @Delete
//    suspend fun delete(episode: Episode)
// }