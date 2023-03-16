package com.example.rickandmorty.domain.character

import com.example.rickandmorty.domain.CharacterClient

class GetCharacterUseCase(
    private val characterClient: CharacterClient,
) {
    suspend fun sortByName(name: String = ""): List<Character>? {
        return characterClient
            .getCharacters(name)?.characters
            ?.sortedBy { it.name }
    }

    suspend fun sortById(name: String = "", page: Int = 1): CharacterData {
        val characterData = characterClient.getCharacters(name, page)
        return CharacterData(
            characters = characterData?.characters
                ?.sortedBy { it.ID },
            pages = characterData?.pages
        )
    }

    suspend fun specificCharacter(code: String): DetailedCharacter? {
        return characterClient.getSingleCharacter(code)
    }
}