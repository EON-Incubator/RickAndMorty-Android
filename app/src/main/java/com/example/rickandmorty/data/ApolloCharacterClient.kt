package com.example.rickandmorty.data

import com.apollographql.apollo3.ApolloClient
import com.example.AllLocationsQuery
import com.example.CharactersQuery
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.location.Location

class ApolloCharacterClient(private val apolloClient: ApolloClient) : CharacterClient {
    override suspend fun getCharacters(): List<Character> {
        return apolloClient
            .query(CharactersQuery())
            .execute()
            .data
            ?.characters
            ?.results
            ?.mapNotNull { it?.toCharacter() }
            ?: emptyList<Character>()
    }

    override suspend fun getAllLocations(): List<Location> {
        return apolloClient
            .query(AllLocationsQuery())
            .execute()
            .data
            ?.locations
            ?.results
            ?.mapNotNull { it?.toAllLocations() }
            ?: emptyList<Location>()
    }
}