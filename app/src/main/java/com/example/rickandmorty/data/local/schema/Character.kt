package com.example.rickandmorty.data.local.schema

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey
    val ID: String,
    val name: String?,
    val image: String?,
    val species: String?,
    val status: String?,
    val gender: String?,
    val episode: String?,
    val lastseen: String?,
    val lastseenId: String?,
    val originId: String?,
    val origin: String?,
)