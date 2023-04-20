package com.example.rickandmorty.api

import com.apollographql.apollo3.ApolloClient
import com.example.rickandmorty.data.ApolloCharacterClient
import com.example.rickandmorty.data.local.repository.CharactersRepository
import com.example.rickandmorty.data.local.repository.EpisodesRepository
import com.example.rickandmorty.data.local.repository.LocationsRepository
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
    fun provideGetCharacterUseCase(
        characterClient: CharacterClient,
        charactersRepository: CharactersRepository,
        episodesRepository: EpisodesRepository,
    ): GetCharacterUseCase {
        return GetCharacterUseCase(characterClient, charactersRepository, episodesRepository)
    }

    @Provides
    @Singleton
    fun provideGetAllEpisodeUseCase(
        characterClient: CharacterClient,
        episodesRepository: EpisodesRepository,
    ): GetAllEpisodeUseCase {
        return GetAllEpisodeUseCase(characterClient, episodesRepository)
    }

    @Provides
    @Singleton
    fun provideGetAllLocationUseCase(
        characterClient: CharacterClient,
        locationsRepository: LocationsRepository,
    ):
        GetAllLocationUseCase {
        return GetAllLocationUseCase(characterClient, locationsRepository)
    }

    @Provides
    @Singleton
    fun provideGetLocationDetailUseCase(
        characterClient: CharacterClient,
        locationsRepository: LocationsRepository,
        charactersRepository: CharactersRepository,
    ):
        GetLocationDetailUseCase {
        return GetLocationDetailUseCase(characterClient, locationsRepository, charactersRepository)
    }

    @Provides
    @Singleton
    fun provideGetEpisodeUseCase(
        characterClient: CharacterClient,
        charactersRepository: CharactersRepository,
        episodesRepository: EpisodesRepository,
    ):
        GetEpisodeUseCase {
        return GetEpisodeUseCase(characterClient, episodesRepository, charactersRepository)
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
}