package com.example.rickandmorty.viewModels.character

import androidx.lifecycle.SavedStateHandle
import com.example.rickandmorty.domain.character.GetCharacterUseCase
import com.example.rickandmorty.domain.episodeusecase.data.repository.FakeRepo
import com.example.rickandmorty.rules.TestDispatcherRule
import com.example.rickandmorty.ui.screens.character.DetailedCharacterViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailsScreenTesting() {

    private lateinit var fakeRepo: FakeRepo
    private lateinit var viewModel: DetailedCharacterViewModel

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun initialSetup() {
        fakeRepo = FakeRepo()
        viewModel = DetailedCharacterViewModel(
            getCharacterUseCase = GetCharacterUseCase(fakeRepo),
            savedStateHandle = SavedStateHandle(
                mapOf("id" to "1")
            )
        )
    }
    @Test
    fun `when getting a character,checking data type is of detailedcharacterScreen`() {
        assert(viewModel.character.value is DetailedCharacterViewModel.detailedcharacterState)
    }

    @Test
    fun `when getting a character, confirming the data in state`() {
        assertEquals(viewModel.character.value.character?.ID, "ID")
        assert(!viewModel.character.value.isLoading)
        assertFalse(viewModel.character.value.isLoading)
        assertNotEquals(viewModel.character.value.character?.origin, "origin1")
        assertEquals(viewModel.character.value.character?.lastseen, "location1")
        assertEquals(viewModel.character.value.character?.lastseenId, "loci1ID")
        assertEquals(viewModel.character.value.character?.origin, "origin1ID")
        assertEquals(viewModel.character.value.character?.originId, "origin1")
    }
}