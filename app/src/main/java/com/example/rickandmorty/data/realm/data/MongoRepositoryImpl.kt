package com.example.rickandmorty.data.realm.data

import com.example.rickandmorty.data.realm.schema.Character
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MongoRepositoryImpl(val realm: Realm) : MongoRepository {

    override fun getData(): Flow<List<com.example.rickandmorty.data.realm.schema.Character>> {
        return realm.query<com.example.rickandmorty.data.realm.schema.Character>().asFlow()
            .map {
                it.list
            }
    }

    override suspend fun insertPerson(person: Character) {
        realm.write { copyToRealm(person) }
    }
}