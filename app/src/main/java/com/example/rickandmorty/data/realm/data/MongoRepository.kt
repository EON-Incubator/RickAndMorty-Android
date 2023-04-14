package com.example.rickandmorty.data.realm.data

import com.example.rickandmorty.data.realm.schema.Character
import kotlinx.coroutines.flow.Flow

interface MongoRepository {
    fun getData(): Flow<List<Character>>
    suspend fun insertPerson(person: Character)
}