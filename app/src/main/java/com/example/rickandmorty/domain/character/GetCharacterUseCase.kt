package com.example.rickandmorty.domain.character

class GetCharacterUseCase(
    private val characterClient: CharacterClient,
) {
    suspend fun execute(): List<com.example.rickandmorty.domain.character.Character> {
        return characterClient
            .getCharacters()
            .sortedBy { it.name }
    }
}