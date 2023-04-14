package com.example.rickandmorty.domain.location

import com.example.rickandmorty.domain.CharacterClient
import com.example.type.FilterLocation

/**
 * Class that gets the Data from CharacterClients
 * and perform functions such as
 * sorting and filtering
 **/
class GetAllLocationUseCase(
    private val characterClient: CharacterClient,
//    private val locationsRepository: LocationsRepository,
) {

    suspend fun execute(filterLocation: FilterLocation = FilterLocation(), page: Int = 1): LocationData {
        val locationData = characterClient
            .getAllLocations(filterLocation, page)

//        val locationDataInDB = locationsRepository
//            .getAllLocationsStream()

//        val locationDataOffline = locationDataInDB
//            .map {
//                LocationData(
//                    locations =
//                    locations = listOf(
//                        it.forEach {
//                            Location(
//                                id = it.id,
//                                name = it.name,
//                                type = it.type,
//                                dimension = it.dimension,
//                                images = listOf(""),
//                                created = it.created,
//                            )
//                        }
//                    ),
//                    pages = Paginate(1,2,1,1)
//                )
//            }

        return LocationData(
            locations = locationData?.locations,
            pages = locationData?.pages
        )
    }
}