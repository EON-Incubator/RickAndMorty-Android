package com.example.rickandmorty.data.local.offlineRepository

import com.example.rickandmorty.data.local.CharacterDao
import com.example.rickandmorty.data.local.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import com.example.rickandmorty.data.local.schema.Character

class OfflineCharactersRepository(private val characterDao: CharacterDao) : CharactersRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    override fun getAllCharactersStream(): Flow<List<Character>> = characterDao.getAllCharacters()

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    override fun getCharacterStream(id: Int): Flow<Character?> = characterDao.getCharacter(id)

    /**
     * Insert item in the data source
     */
    override suspend fun insertCharacter(character: Character) = characterDao.insert(character)

    /**
     * Delete item from the data source
     */
    override suspend fun deleteCharacter(character: Character) = characterDao.delete(character)

    /**
     * Update item in the data source
     */
    override suspend fun updateCharacter(character: Character) = characterDao.update(character)
}