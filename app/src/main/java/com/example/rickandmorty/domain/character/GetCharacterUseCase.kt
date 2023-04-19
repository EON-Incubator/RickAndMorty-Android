package com.example.rickandmorty.domain.character

import androidx.compose.runtime.mutableStateOf
import com.example.rickandmorty.data.local.repository.CharactersRepository
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.network.ConnectivityObserver
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

    suspend fun sortById(
        filterCharacter: FilterCharacter = FilterCharacter(),
        page: Int = 1,
        internetStatus: ConnectivityObserver.Status = ConnectivityObserver.Status.Lost,
    ): CharacterData? {
//        Log.v("GetCharacterUseCase", internetStatus?.name.toString())

        var characterData: CharacterData? = null

//        Log.v("Internet Connection", internetStatus.toString())
        if (internetStatus == ConnectivityObserver.Status.Available) {
            characterData = characterClient.getCharacters(filterCharacter, page)
        } else {
            var data =
                mutableStateOf(emptyList<com.example.rickandmorty.data.local.schema.Character>())

            charactersRepository.getAllCharacterByPageNum(page).map {
                data.value = it
            }.stateIn(
                scope = CoroutineScope(Dispatchers.IO)
            )

            var characterList: List<Character> =
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
                count = 20,
                prev = null,
                next = null
            )

            characterData = CharacterData(
                characters = characterList,
                pages = pagesOffline
            )
//            characterOffline.forEach {
//                Log.v("Page: $page", it.name.toString())
//            }
        }

        return characterData ?: null

//        return CharacterData(
//            characters = characterData?.characters
//                ?.sortedBy { it.ID },
//            pages = characterData?.pages
//        )
    }

    suspend fun specificCharacter(
        code: String,
        internetStatus: ConnectivityObserver.Status = ConnectivityObserver.Status.Lost,
    ): DetailedCharacter? {
        return characterClient.getSingleCharacter(code)
    }
}