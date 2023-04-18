package com.example.rickandmorty.viewModels.character

import com.example.rickandmorty.domain.character.GetCharacterUseCase
import com.example.rickandmorty.fakeRepo.FakeRepo
import com.example.rickandmorty.rules.TestDispatcherRule
import com.example.rickandmorty.ui.screens.character.CharacterViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterViewModelTest {
    private lateinit var viewModel: CharacterViewModel
    private lateinit var fakeRepo: FakeRepo

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun initialSetup() {
        fakeRepo = FakeRepo()
        viewModel = CharacterViewModel(getCharacterUseCase = GetCharacterUseCase(fakeRepo))
    }

    @Test
    fun `When getting all characters, state is shown`() {
        assert(viewModel.characters.value is CharacterViewModel.CharacterState)
    }

    @Test
    fun `When getting all characters, checking correct data is getting here`() {
        runTest {
            viewModel.refresh()
        }
        assertEquals("ID", viewModel.characters.value.characters.get(0).ID)
        assertEquals(3, viewModel.characters.value.pages?.next)
    }

    @Test
    fun `When getting all characters, gender state and alive status changing with business logic or not `() {
        viewModel.changeGender("")
        assertEquals("", viewModel.gender)
        viewModel.changeStatus("")
        assertEquals("", viewModel.status)
    }

    @Test
    fun `checking selectFilter method is running or not `() {
        viewModel.gender = "Female"
        viewModel.status = "Alive"
        viewModel.selectFilter()

        assertEquals("Female", viewModel.gender)
        assertEquals("Alive", viewModel.status)
        assertNotEquals("Dead", viewModel.status)
    }
}