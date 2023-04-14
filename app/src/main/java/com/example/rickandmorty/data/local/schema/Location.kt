package com.example.rickandmorty.data.local.schema

import com.example.rickandmorty.domain.character.DetailedCharacter
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

// import androidx.room.PrimaryKey

// @Entity(tableName = "locations")
class Location : RealmObject {
    @PrimaryKey
    var id: String = ""
    var name: String = ""
    var type: String = ""
    var dimension: String = ""

    //    @Ignore
    var images: RealmList<String> = realmListOf()

    //    @Ignore
//    var residents: RealmList<Character> = realmListOf()
    var residents: RealmList<String> = realmListOf()

    constructor(
        id: String = "",
        name: String = "",
        type: String = "",
        dimension: String = "",
        images: List<String> = emptyList(),
        residents: List<com.example.rickandmorty.domain.character.DetailedCharacter>,
    ) {
        this.id = id
        this.name = name
        this.type = type
        this.dimension = dimension
        this.images = images?.map {
            it
        }?.toRealmList() ?: realmListOf()
//        this.residents = residents?.map {
//            Character(
//                ID = it.ID ?: "",
//                name = it.name ?: "",
//                image = it.image ?: "",
//                species = it.species ?: "",
//                status = it.status ?: "",
//                gender = it.gender ?: "",
//                episode = realmListOf(),
//                lastseen = "",
//                lastseenId = "",
//                originId = "",
//                origin = "",
//            )
//
//        }?.toRealmList() ?: realmListOf()
        this.residents = residents?.map {
            it?.ID.toString()
        }?.toRealmList() ?: realmListOf()
    }

    constructor() {}
}