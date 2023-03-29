package com.example.rickandmorty.viewModels.search

import com.example.rickandmorty.domain.character.GetCharacterUseCase
import com.example.rickandmorty.domain.episodeusecase.data.repository.FakeRepo
import com.example.rickandmorty.domain.location.GetAllLocationUseCase
import com.example.rickandmorty.domain.search.GetSearchResultUseCase
import com.example.rickandmorty.rules.TestDispatcherRule
import com.example.rickandmorty.ui.screens.search.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel
    private lateinit var fakeRepo: FakeRepo

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun setup() {
        fakeRepo = FakeRepo()

        viewModel = SearchViewModel(
            GetSearchResultUseCase(fakeRepo),
            GetAllLocationUseCase(fakeRepo),
            GetCharacterUseCase(fakeRepo)
        )

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when getting all results, confirmaing the data in state`() {
//        assert(!viewModel.searchResult.value.isLoading)
//        runTest {
//            viewModel.onSearch("Rick")
//        }
        runTest {
            viewModel.onSearch("Rick")
        }

        Assert.assertEquals(viewModel.searchResult.value.characterData?.characters?.get(0)?.ID, "1")
        Assert.assertEquals(
            viewModel.searchResult.value.characterData?.characters?.get(0)?.name,
            "Rick"
        )
        Assert.assertEquals(
            viewModel.searchResult.value.characterData?.characters?.get(0)?.image,
            ""
        )
        Assert.assertEquals(
            viewModel.searchResult.value.characterData?.characters?.get(0)?.status,
            "Alive"
        )
        Assert.assertEquals(
            viewModel.searchResult.value.characterData?.characters?.get(0)?.species,
            "Human"
        )
        Assert.assertEquals(
            viewModel.searchResult.value.characterData?.characters?.get(0)?.gender,
            "Male"
        )
        Assert.assertEquals(viewModel.searchResult.value.locationByName?.locations?.get(0)?.id, "1")
        Assert.assertEquals(
            viewModel.searchResult.value.locationByName?.locations?.get(0)?.name,
            "Earth"
        )
        Assert.assertEquals(
            viewModel.searchResult.value.locationByName?.locations?.get(0)?.type,
            "Planet"
        )
        Assert.assertEquals(
            viewModel.searchResult.value.locationByName?.locations?.get(0)?.dimension,
            "Unknown"
        )
        Assert.assertEquals(
            viewModel.searchResult.value.locationByType?.locations?.get(0)?.created,
            ""
        )
        Assert.assertEquals(
            viewModel.searchResult.value.locationByType?.locations?.get(0)?.name,
            "Earth"
        )
        Assert.assertEquals(
            viewModel.searchResult.value.locationByType?.locations?.get(0)?.type,
            "Planet"
        )
        Assert.assertEquals(
            viewModel.searchResult.value.locationByType?.locations?.get(0)?.dimension,
            "Unknown"
        )
        runTest {
            viewModel.updateCharacterList()
            viewModel.updateLocationList()
        }
    }
}