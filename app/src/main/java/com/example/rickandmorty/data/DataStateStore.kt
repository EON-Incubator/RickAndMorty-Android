package com.example.rickandmorty.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStateStore(private val context: Context) {

    companion object {
        private val Context.dataStoree: DataStore<Preferences> by preferencesDataStore("dataState")
        val IS_LOCAL = booleanPreferencesKey("is_local")
    }

    // get the saved email
    val isLocal: Flow<Boolean?> = context.dataStoree.data
        .map { preferences ->
            preferences[IS_LOCAL] ?: false
        }

    // save email into datastore
    suspend fun saveLocalDataState(isLocal: Boolean) {
        context.dataStoree.edit { preferences ->
            preferences[IS_LOCAL] = isLocal
        }
    }
}