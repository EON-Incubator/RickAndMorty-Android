package com.example.rickandmorty.domain.character

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.rickandmorty.data.local.repository.CharactersRepository
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.Paginate
import com.example.type.FilterCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val characterClient: CharacterClient,
    private val charactersRepository: CharactersRepository,
) {
    suspend fun sortByName(filterCharacter: FilterCharacter = FilterCharacter()): List<Character>? {
        return characterClient
            .getCharacters(filterCharacter)?.characters
            ?.sortedBy { it.name }
    }

    suspend fun sortById(filterCharacter: FilterCharacter = FilterCharacter(), page: Int = 1): CharacterData {
        val characterData = characterClient.getCharacters(filterCharacter, page)

        var data = mutableStateOf(emptyList<com.example.rickandmorty.data.local.schema.Character>())

        charactersRepository.getAllCharacterByPageNum(page).map {
            data.value = it
        }.stateIn(
            scope = CoroutineScope(Dispatchers.IO)
        )

        var characterOffline: List<Character> =
            data.value.map { character ->
                Character(
                    gender = character.gender,
                    species = character.species,
                    status = character.status,
                    ID = character.ID,
                    name = character.name,
                    image = character.image
                )
            }

        var pagesOffline = Paginate(
            pages = 1,
            count = 2,
            prev = when (page - 1) {
                0 -> null
                else -> (page - 1)
            },
            next = when (page + 1) {
                0 -> null
                else -> (page - 1)
            }
        )
        characterOffline.forEach {
            Log.v("Page: $page", it.name.toString())
        }

        return CharacterData(
            characters = characterOffline,
            pages = Paginate(2, 42, 1, 826)
        )

//        return CharacterData(
//            characters = characterData?.characters
//                ?.sortedBy { it.ID },
//            pages = characterData?.pages
//        )
    }

    suspend fun specificCharacter(code: String): DetailedCharacter? {
        return characterClient.getSingleCharacter(code)
    }
}