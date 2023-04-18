package com.example.rickandmorty.domain.location

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.rickandmorty.data.local.repository.LocationsRepository
import com.example.rickandmorty.data.local.schema.Location
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.Paginate
import com.example.type.FilterLocation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Class that gets the Data from CharacterClients
 * and perform functions such as
 * sorting and filtering
 **/
class GetAllLocationUseCase @Inject constructor(
    private val characterClient: CharacterClient,
    private val locationsRepository: LocationsRepository,

) {
    suspend fun execute(
        filterLocation: FilterLocation = FilterLocation(),
        page: Int = 1,
    ): LocationData {
        val locationData = characterClient
            .getAllLocations(filterLocation, page)

        var data = mutableStateOf(emptyList<Location>())

        locationsRepository.getAllLocationByPageNum(page).map {
            data.value = it
        }.stateIn(
            scope = CoroutineScope(Dispatchers.IO)
        )

        var locationOffline: List<com.example.rickandmorty.domain.location.Location> =
            data.value.map { location ->
                Location(
                    id = location.id,
                    type = location.type,
                    created = "",
                    images = location.images,
                    name = location.name,
                    dimension = location.dimension
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
        locationOffline.forEach {
            Log.v("Page: $page", it.name.toString())
        }
        return LocationData(
            locations = locationOffline,
            pages = Paginate(2, 42, 1, 826)
        )

        // ** use when internet is on **//
//        return LocationData(
//            locations = locationData?.locations,
//            pages = locationData?.pages
//        )
    }
}