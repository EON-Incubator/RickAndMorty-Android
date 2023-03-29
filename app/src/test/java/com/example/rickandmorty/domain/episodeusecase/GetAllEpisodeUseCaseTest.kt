package com.example.rickandmorty.domain.episodeusecase

import com.example.rickandmorty.domain.episodeusecase.data.repository.FakeRepo

import org.junit.Before

class GetAllEpisodeUseCaseTest {

    private lateinit var getAllEpisodeUseCase: GetAllEpisodeUseCase
    private lateinit var fakeRepo: FakeRepo

    @Before
    fun setUp() {
        fakeRepo = FakeRepo()
        getAllEpisodeUseCase = GetAllEpisodeUseCase(fakeRepo)

//        val episodeToInsert = mutableListOf<Episodes>()
//        ('a'..'z').forEachIndexed { index, c ->
//            episodeToInsert.add(
//                Episodes(
//                    id = c.toString(),
//                    name = c.toString(),
//                    air_date = c.toString(),
//                    episode = c.toString(),
//                    images = emptyList()
//                )
//            )
//        }
//        episodeToInsert.shuffle()
//        runBlocking {
//            episodeToInsert.forEach { fakeRepo.getEpisodes() }
//        }
    }

//    @Test
//    fun getEpisode_verifyList() =
//        runTest {
//            val repository = CharacterClient(
//                apolloClient = FakeApiService()
//            )
//            assertEquals(FakeDataSource.episodesList, repository.getMarsPhotos())
//        }
}