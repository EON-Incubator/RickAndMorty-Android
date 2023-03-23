package com.example.rickandmorty.viewModels.character

import androidx.lifecycle.SavedStateHandle
import com.example.rickandmorty.domain.character.GetCharacterUseCase
import com.example.rickandmorty.domain.episodeusecase.data.repository.FakeRepo
import com.example.rickandmorty.rules.TestDispatcherRule
import com.example.rickandmorty.ui.screens.character.DetailedCharacterViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
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
    fun `when getting a character, confirmaing the data in state`() {
        assertEquals(viewModel.character.value.character?.ID, "ID")
        assert(!viewModel.character.value.isLoading)
        assertNotEquals(viewModel.character.value.character?.origin, "origin1")
    }
}