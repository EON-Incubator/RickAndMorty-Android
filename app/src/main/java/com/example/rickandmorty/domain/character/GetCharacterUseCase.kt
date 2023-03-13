package com.example.rickandmorty.domain.character

import com.example.rickandmorty.domain.CharacterClient

class GetCharacterUseCase(
    private val characterClient: CharacterClient,
) {
    suspend fun sortByName(): List<com.example.rickandmorty.domain.character.Character> {
        return characterClient
            .getCharacters()
            .sortedBy { it.name }
    }

    suspend fun sortById(): List<com.example.rickandmorty.domain.character.Character> {
        return characterClient.getCharacters().sortedBy { it.ID }
    }
}