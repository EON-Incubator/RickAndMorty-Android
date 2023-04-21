package com.example.rickandmorty.data.local.schema

import com.example.rickandmorty.domain.episodes.Episodes
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

// import androidx.room.Entity
// import androidx.room.PrimaryKey

// @Entity(tableName = "characters")
open class Character : RealmObject {
    @PrimaryKey
    var ID: String = ""
    var name: String = ""
    var image: String = ""
    var species: String = ""
    var status: String = ""
    var gender: String = ""
    var lastseen: String = ""
    var lastseenId: String = ""
    var originId: String = ""
    var origin: String = ""

    //    @Ignore
//    var episode: RealmList<Episode>? = null
    var episodes: RealmList<String> = realmListOf()

    constructor(
        ID: String = "",
        name: String = "",
        image: String = "",
        species: String = "",
        status: String = "",
        gender: String = "",
        lastseen: String = "",
        lastseenId: String = "",
        originId: String = "",
        origin: String = "",
        episodes: List<Episodes> = emptyList(),
    ) {
//        this._id = ObjectId().toHexString()
        this.ID = ID ?: ""
        this.name = name ?: ""
        this.image = image ?: ""
        this.species = species ?: ""
        this.status = status ?: ""
        this.gender = gender ?: ""
//        this.episode = episode?.map {
//            Episode(
//                id = it.id ?: "",
//                name = it.name ?: "",
//                episode = it.episode ?: "",
//                air_date = it.air_date ?: "",
//                characters = emptyList()
//            )
//        }?.toRealmList() ?: realmListOf()
        this.episodes = episodes?.map {
            it.id.toString()
        }?.toRealmList() ?: realmListOf()
        this.lastseen = lastseen ?: ""
        this.lastseenId = lastseenId ?: ""
        this.originId = originId ?: ""
        this.origin = origin ?: ""
    }

    constructor() {}
}