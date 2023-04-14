package com.example.rickandmorty.data.realm.schema

import io.realm.kotlin.types.RealmObject

class Character() : RealmObject {

    var id: String = ""
    var name: String = ""
    var type: String = ""
}