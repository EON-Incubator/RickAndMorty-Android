package com.example.rickandmorty.data.local.schema

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes")
data class Episode(
    @PrimaryKey(autoGenerate = true)
    val id: String?,
    val name: String?,
    val episode: String?,
    val air_date: String?,
    val characters: List<Character>,
)