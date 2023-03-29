package com.example.rickandmorty.viewModels.episode

// import kotlinx.coroutines.ExperimentalCoroutinesApi
// import kotlinx.coroutines.test.runTest
import androidx.lifecycle.SavedStateHandle
import com.example.rickandmorty.domain.episodeusecase.GetEpisodeUseCase
import com.example.rickandmorty.domain.episodeusecase.data.repository.FakeRepo
import com.example.rickandmorty.rules.TestDispatcherRule
import com.example.rickandmorty.ui.screens.episode.EpisodeDetailViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EpisodeDetailViewModelTest {

    private lateinit var viewModel: EpisodeDetailViewModel
    private lateinit var fakeRepo: FakeRepo

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun setup() {
        fakeRepo = FakeRepo()
        viewModel = EpisodeDetailViewModel(
            GetEpisodeUseCase(fakeRepo),
            savedStateHandle = SavedStateHandle(mapOf("id" to "1"))
        )
    }

    @Test
    fun `When getting an episode, state is shown`() {
//        runTest {
//            viewModel.getLocationDetail("1")
//        }

        assert(viewModel.state.value is EpisodeDetailViewModel.DetailEpisodesState)
    }

    @Test
    fun `when getting an episode, confirming the data in state`() {
//        runTest {
//            viewModel.getLocationDetail("1")
//        }

        assertEquals(viewModel.state.value.selectedEpisode?.air_date, "airDate1")
        assertNotEquals(viewModel.state.value.selectedEpisode?.episode, "episode2")
//        assert(viewModel.state.value.isLoading)
    }
}