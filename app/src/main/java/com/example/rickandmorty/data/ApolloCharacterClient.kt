package com.example.rickandmorty.data

import CharactersQuery
import com.apollographql.apollo3.ApolloClient
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.CharacterClient

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
}