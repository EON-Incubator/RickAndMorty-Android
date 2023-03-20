package com.example.rickandmorty.viewModels.location

import androidx.lifecycle.SavedStateHandle
import com.example.rickandmorty.rules.TestDispatcherRule
import com.example.rickandmorty.domain.episodeusecase.data.repository.FakeRepo
import com.example.rickandmorty.domain.location.GetLocationDetailUseCase
import com.example.rickandmorty.ui.screens.location.LocationDetailViewModel
import junit.framework.TestCase.assertEquals
// import kotlinx.coroutines.ExperimentalCoroutinesApi
// import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LocationDetailViewModelTest {

    private lateinit var viewModel: LocationDetailViewModel
    private lateinit var fakeRepo: FakeRepo

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun setup() {
        fakeRepo = FakeRepo()
        viewModel = LocationDetailViewModel(
            GetLocationDetailUseCase(fakeRepo),
            savedStateHandle = SavedStateHandle(
                mapOf("id" to "1")
            )
        )
    }

    @Test
    fun `When  getting a locations, state is shown`() {
//        runTest {
//            viewModel.getLocationDetail("1")
//        }

        assert(viewModel.locationDetail.value is LocationDetailViewModel.LocationDetailUiState)
    }

    @Test
    fun `when getting a location, confirmaing the data in state`() {
//        runTest {
//            viewModel.getLocationDetail("1")
//        }

        assertEquals(viewModel.locationDetail.value.locationDetail.dimension, "dimension1")
        assertNotEquals(viewModel.locationDetail.value.locationDetail.type, "type2")
        assert(viewModel.locationDetail.value.isLoading)
    }
}