package com.example.rickandmorty.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmorty.data.local.CharacterDao
import com.example.rickandmorty.data.local.LocationDao
import com.example.rickandmorty.data.local.EpisodeDao
import com.example.rickandmorty.data.local.schema.Character
import com.example.rickandmorty.data.local.schema.Episode
import com.example.rickandmorty.data.local.schema.Location

@Database(
    entities = [
        Character::class,
        Location::class,
        Episode::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RickAndMortyDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao
    abstract fun episodeDao(): EpisodeDao

    companion object {
        @Volatile
        private var Instance: RickAndMortyDatabase? = null

        fun getDatabase(context: Context): RickAndMortyDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, RickAndMortyDatabase::class.java, "rick_database")
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}