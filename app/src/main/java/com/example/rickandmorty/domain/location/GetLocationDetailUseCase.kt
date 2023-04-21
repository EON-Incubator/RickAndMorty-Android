package com.example.rickandmorty.domain.location

import android.util.Log
import com.example.rickandmorty.R
import com.example.rickandmorty.data.local.repository.CharactersRepository
import com.example.rickandmorty.data.local.repository.LocationsRepository
import com.example.rickandmorty.domain.CharacterClient
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.network.ConnectivityObserver

/**
 * Class that gets the Data from CharacterClients
 * and perform functions such as
 * sorting and filtering
 **/
class GetLocationDetailUseCase(
    private val characterClient: CharacterClient,
    private val locationsRepository: LocationsRepository,
    private val charactersRepository: CharactersRepository,
) {

    suspend fun execute(
        id: String,
        internetStatus: ConnectivityObserver.Status = ConnectivityObserver.Status.Lost,
    ): LocationDetail? {
        Log.v(R.string.cc_status.toString(), internetStatus.name.toString())
        if (internetStatus == ConnectivityObserver.Status.Available) {
            return characterClient
                .getLocationDetail(id)
        } else {
            val locationRealm = locationsRepository.getLocationStream(id.toInt())
            Log.v(R.string.location_realm_leading_caps.toString(), locationRealm?.id.toString())
            var id: List<Int> = locationRealm?.residents?.map {
                it.toInt()
            } ?: emptyList()

            var residents = id.map {
                charactersRepository.getCharacterStream(it)
            }.map {
                DetailedCharacter(
                    name = it?.name,
                    image = it?.image,
                    ID = it?.ID,
                    status = it?.status,
                    species = it?.species,
                    gender = it?.gender,
                    episode = emptyList(),
                    lastseen = it?.lastseen,
                    lastseenId = it?.lastseenId,
                    origin = it?.origin,
                    originId = it?.originId
                )
            }
//            var residents = id.forEach {
//                var eachResi=charactersRepository.getCharacterStream(it.toInt())
//
//            }

//            id.forEach {
//                Log.v("Strings", id.toString())
//            }
            val locationDetail = LocationDetail(
                type = locationRealm?.type,
                name = locationRealm?.name,
                dimension = locationRealm?.dimension,
                residents = residents
            )

            return locationDetail
        }
    }
}