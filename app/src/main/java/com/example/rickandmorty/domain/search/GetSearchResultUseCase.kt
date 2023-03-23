package com.example.rickandmorty.domain.search

import android.util.Log
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.character.CharacterData
import com.example.rickandmorty.domain.location.LocationData

/**
 * Class that gets the Data from CharacterClients
 * and perform functions such as
 * sorting and filtering
 **/
class GetSearchResultUseCase(
    private val characterClient: CharacterClient,
) {

    suspend fun execute(queryString: String, page: Int = 1): SearchResult? {
        var resultData = characterClient.getSearchResult(queryString)
        var pageNum = page
        while (
            resultData?.characterData?.pages?.next != null ||
            resultData?.locationByName?.pages?.next != null ||
            resultData?.locationByType?.pages?.next != null
        ) {
            Log.v("Search test:Character", resultData?.characterData?.pages?.next.toString())
            Log.v("Search test: by name", resultData?.locationByName?.pages?.next.toString())
            Log.v("Search test: by type", resultData?.locationByType?.pages?.next.toString())
            Log.v("Search test: by pageNum", pageNum.toString())
            val temp = characterClient.getSearchResult(queryString, ++pageNum)
            resultData = resultData?.copy(
                characterData = CharacterData(
                    characters =
                    resultData.characterData?.characters?.plus(
                        temp?.characterData?.characters ?: emptyList()
                    ),
                    pages = temp?.characterData?.pages
                ),
                locationByName = LocationData(
                    locations =
                    resultData.locationByName?.locations?.plus(
                        temp?.locationByName?.locations ?: emptyList()
                    ),
                    pages = temp?.locationByName?.pages
                ),
                locationByType = LocationData(
                    locations = resultData.locationByType?.locations?.plus(
                        temp?.locationByType?.locations ?: emptyList()
                    ),
                    pages = temp?.locationByType?.pages
                )
            )
        }
//        Log.v("Search test:Character", resultData?.characterData?.characters?.size.toString())
//        Log.v("Search test: by name", resultData?.locationByName?.locations?.size.toString())
//        Log.v("Search test: by type", resultData?.locationByType?.locations?.size.toString())

        return resultData?.copy(
            locationByName = resultData?.locationByName?.copy(
                locations = resultData.locationByName?.locations?.plus(
                    resultData?.locationByType?.locations
                        ?: emptyList()
                )?.distinct()
            )
        )
    }
}