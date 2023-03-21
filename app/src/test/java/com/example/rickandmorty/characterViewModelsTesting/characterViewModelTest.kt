package com.example.rickandmorty.characterViewModelsTesting

import com.example.rickandmorty.domain.character.GetCharacterUseCase
import com.example.rickandmorty.domain.episodeusecase.data.repository.FakeRepo
import com.example.rickandmorty.rules.TestDispatcherRule
import com.example.rickandmorty.ui.screens.character.CharacterViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class characterViewModelTest() {
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
        assertEquals(viewModel.characters.value.characters.get(0).ID, "ID")
        assertEquals(viewModel.characters.value.pages?.next, 3)
    }
}