package com.example.rickandmorty.domain.character

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

    suspend fun specific(code: String): com.example.rickandmorty.domain.character.Character ? {
        return characterClient.getSingleCharacter(code)
    }
}