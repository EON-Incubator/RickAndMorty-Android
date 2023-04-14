package com.example.rickandmorty.data.local.schema

// import androidx.room.TypeConverter
// import com.google.gson.Gson
// import com.google.gson.reflect.TypeToken
//
// class EpisodeListTypeConverter {
//
//    @TypeConverter
//    fun fromString(value: String?): List<Episode>? {
//        val type = object : TypeToken<List<Episode>>() {}.type
//        return Gson().fromJson<List<Episode>>(value, type)
//    }
//
//    @TypeConverter
//    fun toString(list: List<Episode>?): String? {
//        return Gson().toJson(list)
//    }
// }
//
// class CharacterListTypeConverter {
//
//    @TypeConverter
//    fun fromString(value: String?): List<Character>? {
//        val type = object : TypeToken<List<Character>>() {}.type
//        return Gson().fromJson<List<Character>>(value, type)
//    }
//
//    @TypeConverter
//    fun toString(list: List<Character>?): String? {
//        return Gson().toJson(list)
//    }
// }
//
// class LocationListTypeConverter {
//
//    @TypeConverter
//    fun fromString(value: String?): List<Location>? {
//        val type = object : TypeToken<List<Location>>() {}.type
//        return Gson().fromJson<List<Location>>(value, type)
//    }
//
//    @TypeConverter
//    fun toString(list: List<Location>?): String? {
//        return Gson().toJson(list)
//    }
// }