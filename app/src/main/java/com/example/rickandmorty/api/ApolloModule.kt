package com.example.rickandmorty.api

import android.content.Context
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.example.rickandmorty.data.ApolloCharacterClient
import com.example.rickandmorty.data.local.database.RickAndMortyDatabase
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.character.GetCharacterUseCase
import com.example.rickandmorty.domain.episodes.GetAllEpisodeUseCase
import com.example.rickandmorty.domain.episodes.GetEpisodeUseCase
import com.example.rickandmorty.domain.localRealm.GetAllDataUseCase
import com.example.rickandmorty.domain.location.GetAllLocationUseCase
import com.example.rickandmorty.domain.location.GetLocationDetailUseCase
import com.example.rickandmorty.domain.search.GetSearchResultUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
with @Module annotation it tells the dagger hilt check this module while performing
dependency injection

SingletonComponent::class allows us to make the object of this just one time on Application level

 */
@Module
@InstallIn(SingletonComponent::class)
object ApolloModule {
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder().serverUrl("https://rickandmortyapi.com/graphql").build()
    }

/**
passed apollo client directly in the implementation of
provideGetCharactersClient() method for abstraction
*/
    @Provides
    @Singleton
    fun provideGetCharactersClient(apolloClient: ApolloClient): CharacterClient {
        return ApolloCharacterClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetCharacterUseCase(characterClient: CharacterClient): GetCharacterUseCase {
        return GetCharacterUseCase(characterClient)
    }

    @Provides
    @Singleton
    fun provideGetAllEpisodeUseCase(characterClient: CharacterClient): GetAllEpisodeUseCase {
        return GetAllEpisodeUseCase(characterClient)
    }

    @Provides
    @Singleton
    fun provideGetAllLocationUseCase(characterClient: CharacterClient):
        GetAllLocationUseCase {
        return GetAllLocationUseCase(characterClient)
    }

    @Provides
    @Singleton
    fun provideGetLocationDetailUseCase(characterClient: CharacterClient):
        GetLocationDetailUseCase {
        return GetLocationDetailUseCase(characterClient)
    }

    @Provides
    @Singleton
    fun provideGetEpisodeUseCase(characterClient: CharacterClient):
        GetEpisodeUseCase {
        return GetEpisodeUseCase(characterClient)
    }

    @Provides
    @Singleton
    fun provideGetSearchResultUseCase(characterClient: CharacterClient): GetSearchResultUseCase {
        return GetSearchResultUseCase(characterClient)
    }

    @Provides
    @Singleton
    fun provideGetAllDataUseCase(characterClient: CharacterClient): GetAllDataUseCase {
        return GetAllDataUseCase(characterClient)
    }



    @Volatile
    private var Instance: RickAndMortyDatabase? = null

    @Provides
    @Singleton
    fun provide(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        RickAndMortyDatabase::class.java,
        "rick_database"
    )
        .fallbackToDestructiveMigration()
        .build()
        .also { Instance = it }

    @Provides
    @Singleton
    fun provideDao(db: RickAndMortyDatabase) = db.episodeDao()

    @Provides
    @Singleton
    fun provideDaoLocation(db: RickAndMortyDatabase) = db.locationDao()

    @Provides
    @Singleton
    fun provideDaoCharacter(db: RickAndMortyDatabase) = db.characterDao()

//    @Provides
//    fun provideEntity() = Episode()
}