package com.example.rickandmorty.domain.character

class GetCharacterUseCase(
    private val characterClient: CharacterClient
) {
    suspend fun execute(): List<Character> {
        return characterClient
            .getCharacters()
            .sortedBy { it.name }
    }
}