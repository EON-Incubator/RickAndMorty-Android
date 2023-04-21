package com.example.rickandmorty.api

// import android.content.Context
// import com.example.rickandmorty.data.local.database.RickAndMortyDatabase
// import com.example.rickandmorty.data.local.offlineRepository.OfflineCharactersRepository
// import com.example.rickandmorty.data.local.offlineRepository.OfflineEpisodesRepository
// import com.example.rickandmorty.data.local.offlineRepository.OfflineLocationsRepository
// import com.example.rickandmorty.data.local.repository.CharactersRepository
// import com.example.rickandmorty.data.local.repository.EpisodesRepository
// import com.example.rickandmorty.data.local.repository.LocationsRepository

/**
 * App container for Dependency injection.
 */
// interface RoomModule {
//    val charactersRepository: CharactersRepository
//    val episodesRepository: EpisodesRepository
//    val locationsRepository: LocationsRepository
// }
//
// /**
// * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
// */
// class RoomDataContainer(private val context: Context) : RoomModule {
//    /**
//     * Implementation for [ItemsRepository]
//     */
//    override val charactersRepository: CharactersRepository by lazy {
//        OfflineCharactersRepository(RickAndMortyDatabase.getDatabase(context).characterDao())
//    }
//
//    override val episodesRepository: EpisodesRepository by lazy {
//        OfflineEpisodesRepository(RickAndMortyDatabase.getDatabase(context).episodeDao())
//    }
//
//    override val locationsRepository: LocationsRepository by lazy {
//        OfflineLocationsRepository(RickAndMortyDatabase.getDatabase(context).locationDao())
//    }
// }