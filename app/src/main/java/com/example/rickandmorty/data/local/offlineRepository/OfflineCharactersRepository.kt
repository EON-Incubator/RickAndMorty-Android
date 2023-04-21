package com.example.rickandmorty.data.local.offlineRepository

import android.util.Log
import com.example.rickandmorty.data.local.repository.CharactersRepository
import com.example.rickandmorty.data.local.schema.Character
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// class OfflineCharactersRepository(private val characterDao: CharacterDao) : CharactersRepository {
class OfflineCharactersRepository(private val realm: Realm) : CharactersRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    override fun getAllCharactersStream(queryString: String): Flow<List<Character>> {
        if (queryString.isNotEmpty()) {
            return realm.query<Character>(query = "name CONTAINS[c] $0", queryString).asFlow().map { it.list }
        }
        return realm.query<Character>().asFlow().map { it.list }
    }

    override fun getAllCharacterByPageNum(filterCharacter: Map<String, String>, page: Int): Flow<List<Character>> {
        Log.v("Fil Char", filterCharacter.toString())
        if (filterCharacter.isNotEmpty()) {
            return realm.query<Character>(
                query = "gender CONTAINS[c] $0 AND status CONTAINS[c] $1",
                filterCharacter["gender"],
                filterCharacter["status"]
            ).asFlow().map { it.list }
        }
        return realm.query<Character>().asFlow().map { it.list }
    }

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    override fun getCharacterStream(id: Int): Character? {
        return realm.query<Character>(query = "ID == $0", id.toString()).first().find()
    }

    /**
     * Insert item in the data source
     */
    override suspend fun insertCharacter(character: Character) {
        realm.write {
            if (query<Character>(query = "ID == $0", character.ID).first().find() != null) {
                var queriedCharacter =
                    query<Character>(query = "ID == $0", character.ID).first().find()
                queriedCharacter = character
            } else {
                copyToRealm(character)
            }
        }
    }

    /**
     * Delete item from the data source
     */
    override suspend fun deleteCharacter(character: Character) {
        realm.write {
            val person = query<Character>(query = "ID == $0", character.ID).first().find()
            try {
                person?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("MongoRepositoryImpl", "${e.message}")
            }
        }
    }

    /**
     * Update item in the data source
     */
    override suspend fun updateCharacter(character: Character) {
        realm.write {
            var queriedCharacter = query<Character>(query = "ID == $0", character.ID).first().find()
            queriedCharacter = character
        }
    }
}