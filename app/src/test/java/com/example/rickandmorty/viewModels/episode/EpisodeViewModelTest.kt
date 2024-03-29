package com.example.rickandmorty.viewModels.episode

import com.example.rickandmorty.domain.episodes.GetAllEpisodeUseCase
import com.example.rickandmorty.fakeRepo.FakeRepo
import com.example.rickandmorty.rules.TestDispatcherRule
import com.example.rickandmorty.ui.screens.episode.EpisodeViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EpisodeViewModelTest {

    private lateinit var viewModel: EpisodeViewModel
    private lateinit var fakeRepo: FakeRepo

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun setup() {
        fakeRepo = FakeRepo()
        viewModel = EpisodeViewModel(GetAllEpisodeUseCase(fakeRepo))
    }

    @Test
    fun `When getting all episodes, state is shown`() {
        assert(viewModel.state.value is EpisodeViewModel.EpisodesState)
    }

    @Test
    fun `when getting all episodes, confirming the data in state`() {
        assert(!viewModel.state.value.isLoading)
        assertEquals(viewModel.state.value.episodes.get(0).id, "id")
        assertNotEquals(viewModel.state.value.episodes.get(0).name, "name2")
    }

    @Test
    fun `check update episode list is working`() {
        viewModel.updateEpisodeList()
        Assert.assertNull(viewModel.state.value.pages?.next)
        Assert.assertNull(viewModel.state.value.pages?.prev)
    }
}