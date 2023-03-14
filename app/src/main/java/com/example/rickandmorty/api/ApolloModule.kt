package com.example.rickandmorty.api

import com.apollographql.apollo3.ApolloClient
import com.example.rickandmorty.data.ApolloCharacterClient
import com.example.rickandmorty.data.ApolloEpisodeClient
import com.example.rickandmorty.domain.EpisodeClient
import com.example.rickandmorty.domain.character.GetCharacterUseCase
import com.example.rickandmorty.domain.episodeusecase.GetEpisodeUseCase
import com.example.rickandmorty.domain.episodeusecase.GetEpisodesUseCase
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.location.GetAllLocationUseCase
import com.example.rickandmorty.domain.location.GetLocationDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
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
        return ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()
    }

    /*
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
    fun provideGetEpisodeClient(apolloClient: ApolloClient): EpisodeClient {
        return ApolloEpisodeClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetAllLocationUseCase(characterClient: CharacterClient): GetAllLocationUseCase {
        return GetAllLocationUseCase(characterClient)
    }

    @Provides
    @Singleton
    fun provideGetEpisodesUseCase(episodeClient: EpisodeClient): GetEpisodesUseCase {
        return GetEpisodesUseCase(episodeClient)
    }

    @Provides
    @Singleton
    fun provideGetEpisodeUseCase(episodeClient: EpisodeClient): GetEpisodeUseCase {
        return GetEpisodeUseCase(episodeClient)
    }

    @Provides
    @Singleton
    fun provideGetLocationDetailUseCase(characterClient: CharacterClient): GetLocationDetailUseCase {
        return GetLocationDetailUseCase(characterClient)
    }
}