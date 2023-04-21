package com.example.rickandmorty.domain.location

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.rickandmorty.R
import com.example.rickandmorty.data.local.repository.LocationsRepository
import com.example.rickandmorty.data.local.schema.Location
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.network.ConnectivityObserver
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
        internetStatus: ConnectivityObserver.Status = ConnectivityObserver.Status.Lost,
    ): LocationData? {
        var locationData: LocationData? = null
        if (internetStatus == ConnectivityObserver.Status.Available) {
<<<<<<< HEAD
            Log.v(R.string.internet_on.toString(), internetStatus.toString())
            locationData = characterClient
                .getAllLocations(filterLocation, page)
=======
            Log.v("Internet On", internetStatus.toString())
            try {
                locationData = characterClient
                    .getAllLocations(filterLocation, page)
            } catch (e: Exception) {
            }
>>>>>>> 0e26d9cd0d62b050e84e9b67339826830d083882
        } else {
            var data = mutableStateOf(emptyList<Location>())

            locationsRepository.getAllLocationByPageNum(page).map {
                data.value = it
            }.stateIn(
                scope = CoroutineScope(Dispatchers.IO)
            )

            var locationList: List<com.example.rickandmorty.domain.location.Location> =
                data.value.map { location ->
                    Location(
                        type = location.type,
                        created = "",
                        images = location.images,
                        name = location.name,
                        id = location.id,
                        dimension = location.dimension
                    )
                }

            var pagesOffline = Paginate(
                pages = 1,
                count = 20,
                prev = null,
                next = null
            )

            locationData = LocationData(
                locations = locationList,
                pages = pagesOffline
            )
        }

//        locationOffline.forEach {
//            Log.v("Page: $page", it.name.toString())
//        }
        return locationData ?: null

        // ** use when internet is on **//
//        return LocationData(
//            locations = locationData?.locations,
//            pages = locationData?.pages
//        )
    }
}