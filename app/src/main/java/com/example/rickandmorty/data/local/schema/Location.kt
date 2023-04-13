package com.example.rickandmorty.data.local.schema

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class Location(
    @PrimaryKey
    val id: String,
    val name: String?,
    val type: String?,
    val dimension: String?,
    val images: String?,
    val created: String,
    val episode: String?,
    val air_date: String?,
    val characters: String?,
)