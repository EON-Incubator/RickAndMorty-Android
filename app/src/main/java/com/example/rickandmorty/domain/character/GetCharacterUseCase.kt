package com.example.rickandmorty.domain.character

import com.example.rickandmorty.domain.CharacterClient
class GetCharacterUseCase(
    private val characterClient: CharacterClient,
) {
    suspend fun sortByName(name: String = ""): List<Character> {
        return characterClient
            .getCharacters(name)
            .sortedBy { it.name }
    }

    suspend fun sortById(name: String = ""): List<Character> {
        return characterClient.getCharacters(name).sortedBy { it.ID }
    }

    suspend fun specificCharacter(code: String): DetailedCharacter ? {
        return characterClient.getSingleCharacter(code)
    }
}