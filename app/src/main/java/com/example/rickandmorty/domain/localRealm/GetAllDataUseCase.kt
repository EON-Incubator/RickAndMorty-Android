package com.example.rickandmorty.domain.localRealm

import android.util.Log
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.character.CharacterData
import com.example.rickandmorty.domain.episodes.EpisodesData
import com.example.rickandmorty.domain.location.LocationData

class GetAllDataUseCase(
    private val characterClient: CharacterClient,
) {

    suspend fun execute(page: Int = 1): AllData? {
        Log.v("Search test: I am Rumning", "")
        var resultData = characterClient.getAllData()
        var pageNum = page
        while (
            resultData?.characterData?.pages?.next != null ||
            resultData?.locationData?.pages?.next != null ||
            resultData?.episodeData?.pages?.next != null
        ) {
//            Log.v("Search test:Character", resultData?.characterData?.pages?.next.toString())
//            Log.v("Search test: by name", resultData?.locationByName?.pages?.next.toString())
//            Log.v("Search test: by type", resultData?.locationByType?.pages?.next.toString())
            Log.v("Search test: by pageNum", pageNum.toString())
            val temp = characterClient.getAllData(++pageNum)
            resultData = resultData?.copy(
                characterData = CharacterData(
                    characters =
                    resultData.characterData?.characters?.plus(
                        temp?.characterData?.characters ?: emptyList()
                    ),
                    pages = temp?.characterData?.pages
                ),
                locationData = LocationData(
                    locations =
                    resultData.locationData?.locations?.plus(
                        temp?.locationData?.locations ?: emptyList()
                    ),
                    pages = temp?.locationData?.pages
                ),
                episodeData = com.example.rickandmorty.domain.localRealm.EpisodesData(
                    episodes = resultData.episodeData?.episodes?.plus(
                        temp?.episodeData?.episodes ?: emptyList()
                    ),
                    pages = temp?.episodeData?.pages
                )
            )
        }
        Log.v("Search test:Character", resultData?.characterData?.characters?.size.toString())
        Log.v("Search test: by name", resultData?.locationData?.locations?.size.toString())
        Log.v("Search test: by type", resultData?.episodeData?.episodes?.size.toString())

//            resultData?.copy(
//                locationByName = resultData?.locationByName?.copy(
//                    locations = resultData.locationByName?.locations?.plus(
//                        resultData?.locationByType?.locations
//                            ?: emptyList()
//                    )?.distinct()
//                )
//            )

        return resultData
    }
}