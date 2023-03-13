package com.example.rickandmorty.domain.character

interface CharacterClient {
    suspend fun getCharacters(): List<Character>

    suspend fun getSingleCharacter(code: String): DetailedCharacter ?
}