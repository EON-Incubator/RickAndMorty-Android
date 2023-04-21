package com.example.rickandmorty.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.rickandmorty.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStateStore(private val context: Context) {

    companion object {
        private val Context.dataStoree: DataStore<Preferences> by preferencesDataStore(
                    R.string.datastate_all_small_case.toString())
        val IS_LOCAL = booleanPreferencesKey(R.string.is_local.toString())
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