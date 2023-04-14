package com.example.rickandmorty.data.realm.builder

import com.example.rickandmorty.data.realm.data.MongoRepository
import com.example.rickandmorty.data.realm.data.MongoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                com.example.rickandmorty.data.realm.schema.Character::class
            )
        )
            .compactOnLaunch()
            .build()
        return Realm.open(config)
    }

    @Singleton
    @Provides
    fun provideMongoRepository(realm: Realm): MongoRepository {
        return MongoRepositoryImpl(realm = realm)
    }
}