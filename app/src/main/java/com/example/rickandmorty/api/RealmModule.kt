package com.example.rickandmorty.api

import com.example.rickandmorty.data.local.offlineRepository.OfflineCharactersRepository
import com.example.rickandmorty.data.local.offlineRepository.OfflineEpisodesRepository
import com.example.rickandmorty.data.local.offlineRepository.OfflineLocationsRepository
import com.example.rickandmorty.data.local.repository.CharactersRepository
import com.example.rickandmorty.data.local.repository.EpisodesRepository
import com.example.rickandmorty.data.local.repository.LocationsRepository
import com.example.rickandmorty.data.local.schema.Character
import com.example.rickandmorty.data.local.schema.Episode
import com.example.rickandmorty.data.local.schema.Location
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RealmModule {

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                Character::class,
                Episode::class,
                Location::class
            )
        )
            .compactOnLaunch()
            .build()
        return Realm.open(config)
    }

    @Singleton
    @Provides
    fun provideCharacterRepository(realm: Realm): CharactersRepository {
        return OfflineCharactersRepository(realm = realm)
    }

    @Singleton
    @Provides
    fun provideEpisodeRepository(realm: Realm): EpisodesRepository {
        return OfflineEpisodesRepository(realm = realm)
    }

    @Singleton
    @Provides
    fun provideLocationRepository(realm: Realm): LocationsRepository {
        return OfflineLocationsRepository(realm = realm)
    }
}