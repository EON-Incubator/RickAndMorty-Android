package com.example.rickandmorty.viewModels.location

import com.example.marsphotos.rules.TestDispatcherRule
import com.example.rickandmorty.domain.episodeusecase.data.repository.FakeRepo
import com.example.rickandmorty.domain.location.GetAllLocationUseCase
import com.example.rickandmorty.ui.screens.location.LocationViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LocationViewModelTest {

    private lateinit var viewModel: LocationViewModel
    private lateinit var fakeRepo: FakeRepo

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun setup() {
        fakeRepo = FakeRepo()
        viewModel = LocationViewModel(GetAllLocationUseCase(fakeRepo))
    }

    @Test
    fun `When  getting all locations, state is shown`() {
        runTest {
            viewModel.getAllLocations()
        }

        assert(viewModel.location.value is LocationViewModel.LocationUiState)
    }

    @Test
    fun `when getting all location, confirmaing the data in state`() {
        runTest {
            viewModel.getAllLocations()
        }

        assert(!viewModel.location.value.isLoading)
        assertEquals(viewModel.location.value.locations.get(0).id, "id")
        assertNotEquals(viewModel.location.value.locations.get(0).name, "name2")
    }
}