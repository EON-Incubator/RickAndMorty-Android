package com.example.rickandmorty

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
With the help of @HiltAndroidApp
Marking this as a Parent Class for child classes to use dependency
 */
@HiltAndroidApp
class RickAndMortyApp : Application()