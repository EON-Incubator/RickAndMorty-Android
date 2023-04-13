package com.example.rickandmorty.data.local.repository

import kotlinx.coroutines.flow.Flow
import com.example.rickandmorty.data.local.schema.Character

interface CharactersRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllCharactersStream(): Flow<List<Character>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getCharacterStream(id: Int): Flow<Character?>

    /**
     * Insert item in the data source
     */
    suspend fun insertCharacter(item: Character)

    /**
     * Delete item from the data source
     */
    suspend fun deleteCharacter(item: Character)

    /**
     * Update item in the data source
     */
    suspend fun updateCharacter(item: Character)
}