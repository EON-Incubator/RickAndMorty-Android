package com.example.rickandmorty.viewModels.location

import com.example.rickandmorty.domain.episodeusecase.data.repository.FakeRepo
import com.example.rickandmorty.domain.location.GetAllLocationUseCase
import com.example.rickandmorty.rules.TestDispatcherRule
import com.example.rickandmorty.ui.screens.location.LocationViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
        assert(viewModel.location.value is LocationViewModel.LocationUiState)
    }

    @Test
    fun `when getting all location, confirmaing the data in state`() {
        assert(!viewModel.location.value.isLoading)
        assertEquals(viewModel.location.value.locations.get(0).id, "id")
        assertNotEquals(viewModel.location.value.locations.get(0).name, "name2")
    }

    @Test
    fun `check updating list is working`() {
        viewModel.updateList()
        Assert.assertNull(viewModel.location.value.pages?.next)
        Assert.assertNull(viewModel.location.value.pages?.prev)
    }
}