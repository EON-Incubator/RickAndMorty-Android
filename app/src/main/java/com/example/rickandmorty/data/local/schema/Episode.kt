package com.example.rickandmorty.data.local.schema

// import androidx.room.PrimaryKey
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

// @Entity(tableName = "episodes")
class Episode : RealmObject {
    @PrimaryKey
    var id: String = ""
    var name: String = ""
    var episode: String = ""
    var air_date: String = ""
    var overview: String = ""
    var voteAverage: String = ""
    var sliderImages: RealmList<String> = realmListOf()
    var videos: String = ""

    //    @Ignore
//    var characters: RealmList<Character> = realmListOf()
    var characters: RealmList<String> = realmListOf()
    var images: RealmList<String> = realmListOf()

    constructor(
        id: String = "",
        name: String = "",
        episode: String = "",
        air_date: String = "",
        characters: List<com.example.rickandmorty.domain.character.DetailedCharacter>,
        episodeDetail: com.example.rickandmorty.domain.episodes.TmdbEpisodeDetail,
    ) {
        this.id = id ?: ""
        this.name = name ?: ""
        this.episode = episode ?: ""
        this.air_date = air_date ?: ""
//        this.characters = characters?.map {
//            Character(
//                    ID = it.ID ?: "",
//                    name = it.name ?: "",
//                    image = it.image ?: "",
//                    species = it.species ?: "",
//                    status = it.status ?: "",
//                    gender = it.gender ?: "",
//                    episode = realmListOf(),
//                    lastseen = "",
//                    lastseenId = "",
//                    originId = "",
//                    origin = "",
//                )
//
//        }?.toRealmList() ?: realmListOf()
        this.characters = characters?.map {
            it?.ID.toString()
        }?.toRealmList() ?: realmListOf()
        this.images = characters?.map {
            it?.image.toString()
        }?.toRealmList() ?: realmListOf()

        this.overview = episodeDetail.overview
        this.voteAverage = episodeDetail.voteAverage.toString()
        this.sliderImages = episodeDetail.images?.stills?.map {
            it?.filePath.toString()
        }?.toRealmList() ?: realmListOf()
        this.videos = episodeDetail.videos.toString()
    }

    constructor() {}
}