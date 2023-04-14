package com.example.rickandmorty.data.realm.schema

import io.realm.kotlin.types.RealmObject
import org.mongodb.kbson.ObjectId

class Character() : RealmObject {

    var _id: ObjectId = ObjectId.invoke()
    var name: String = ""
    var type: String = ""
}