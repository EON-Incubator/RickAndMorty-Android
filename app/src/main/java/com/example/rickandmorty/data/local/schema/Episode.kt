package com.example.rickandmorty.data.local.schema

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.rickandmorty.domain.episodes.Episodes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@TypeConverters(ListTypeConverter::class)
@Entity(tableName = "episodes")
data class Episode(
    @PrimaryKey
    val id: String = "",
    val name: String? = "",
    val episode: String? = "",
    val air_date: String? = "",
    val characters: String? = "",
    val episodesData: List<Episodes>? = emptyList(),
)

class ListTypeConverter {

    @TypeConverter
    fun fromString(value: String?): List<Episodes>? {
        val type = object : TypeToken<List<Episodes>>() {}.type
        return Gson().fromJson<List<Episodes>>(value, type)
    }

    @TypeConverter
    fun toString(list: List<Episodes>?): String? {
        return Gson().toJson(list)
    }
}