package com.example.rickandmorty.domain.search

import com.example.rickandmorty.domain.CharacterClient

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