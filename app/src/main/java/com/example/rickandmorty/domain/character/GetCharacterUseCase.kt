package com.example.rickandmorty.domain.character

import androidx.compose.runtime.mutableStateOf
import com.example.rickandmorty.data.local.repository.CharactersRepository
import com.example.rickandmorty.data.local.repository.EpisodesRepository
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.domain.episodes.Episodes
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
    private val episodesRepository: EpisodesRepository,
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
        if (internetStatus == ConnectivityObserver.Status.Available) {
            return characterClient.getSingleCharacter(code)
        } else {
            val characterRealm = charactersRepository.getCharacterStream(code.toInt())
            var id: List<Int> = characterRealm?.episodes?.map {
                it.toInt()
            } ?: emptyList()

            var episodes = id.map {
                episodesRepository.getEpisodeStream(it)
            }.map {
                Episodes(
                    id = it?.id,
                    name = it?.name,
                    episode = it?.episode,
                    images = it?.images,
                    air_date = it?.air_date
                )
            }

            val detailedCharacter = DetailedCharacter(
                ID = characterRealm?.ID,
                image = characterRealm?.image,
                status = characterRealm?.status,
                species = characterRealm?.species,
                gender = characterRealm?.gender,
                name = characterRealm?.name,
                episode = episodes,
                originId = characterRealm?.originId,
                origin = characterRealm?.origin,
                lastseenId = characterRealm?.lastseenId,
                lastseen = characterRealm?.lastseen
            )
            return detailedCharacter
        }
    }
}