package com.example.rickandmorty.data.local.repository

import com.example.rickandmorty.data.local.schema.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllCharactersStream(queryString: String = ""): Flow<List<Character>>

    fun getAllCharacterByPageNum(filterCharacter: Map<String, String> = emptyMap(), page: Int = 1): Flow<List<Character>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getCharacterStream(id: Int): Character?

    /**
     * Insert item in the data source
     */
    suspend fun insertCharacter(character: Character)

    /**
     * Delete item from the data source
     */
    suspend fun deleteCharacter(character: Character)

    /**
     * Update item in the data source
     */
    suspend fun updateCharacter(character: Character)
}