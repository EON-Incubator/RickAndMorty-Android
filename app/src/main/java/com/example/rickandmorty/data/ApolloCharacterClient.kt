package com.example.rickandmorty.data

import com.apollographql.apollo3.ApolloClient
import com.example.CharactersQuery
import com.example.SpecificCharacterQuery
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.domain.character.CharacterClient
import com.example.rickandmorty.domain.character.DetailedCharacter

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

    override suspend fun getSingleCharacter(code: String): DetailedCharacter? {
        return apolloClient.query(SpecificCharacterQuery(code))
            .execute().data?.character?.toSpecificChar()
    }
}