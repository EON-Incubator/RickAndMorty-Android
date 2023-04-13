package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.api.RoomDataContainer
import com.example.rickandmorty.api.RoomModule
import dagger.hilt.android.HiltAndroidApp

/**
With the help of @HiltAndroidApp
Marking this as a Parent Class for child classes to use dependency
 */
@HiltAndroidApp
class RickAndMortyApp : Application() {
    lateinit var roomModule: RoomModule

    override fun onCreate() {
        super.onCreate()
        roomModule = RoomDataContainer(this)
    }
}