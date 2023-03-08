package com.example.rickandmorty.domain

interface CharacterClient {
    suspend fun getCharacters(): List<Character>
}