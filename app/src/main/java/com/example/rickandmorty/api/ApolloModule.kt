package com.example.rickandmorty.api

import com.apollographql.apollo3.ApolloClient
import com.example.rickandmorty.data.ApolloCharacterClient
import com.example.rickandmorty.domain.CharacterClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApolloModule {
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun provideGetCharactersClient(apolloClient: ApolloClient): CharacterClient {
        return ApolloCharacterClient(apolloClient)
    }
}