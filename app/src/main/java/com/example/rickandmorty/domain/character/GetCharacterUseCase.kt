package com.example.rickandmorty.domain.character

import com.example.rickandmorty.domain.CharacterClient
import com.example.type.FilterCharacter

class GetCharacterUseCase(
    private val characterClient: CharacterClient,
) {
    suspend fun sortByName(filterCharacter: FilterCharacter = FilterCharacter()): List<Character>? {
        return characterClient
            .getCharacters(filterCharacter)?.characters
            ?.sortedBy { it.name }
    }

    suspend fun sortById(filterCharacter: FilterCharacter = FilterCharacter(), page: Int = 1): CharacterData {
        val characterData = characterClient.getCharacters(filterCharacter, page)
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