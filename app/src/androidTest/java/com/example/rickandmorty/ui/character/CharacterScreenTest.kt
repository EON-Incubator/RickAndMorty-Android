package com.example.rickandmorty.ui.character

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.ui.screens.character.CharacterViewModel
import com.example.rickandmorty.ui.screens.character.Characters
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
class CharacterScreenTest() {

    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var teststate: CharacterViewModel.CharacterState

    @Before
    fun data_getting_initilized() {
        teststate = CharacterViewModel.CharacterState(

            listOf(
                Character(
                    "ID1",
                    "Rick",
                    "https://rickandmortyapi.com/api/character/avatar/12.jpeg",
                    "species1",
                    "alive",
                    "male"
                ),
                Character(
                    "ID2",
                    "Morty",
                    "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                    "species2",
                    "dead",
                    "male"
                ),
                Character(
                    "ID3",
                    "Ann",
                    "https://rickandmortyapi.com/api/character/avatar/5.jpeg",
                    "species3",
                    "alive",
                    "female"
                )
            ),
            null,
            false,
            null,
            pages = Paginate(
                3,
                10,
                1,
                20
            )
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun character_screen_name_top_bar_is_characters() {
        composeTestRule.setContent {
            RickAndMortyTheme() {
                Characters(
                    state = teststate,
                    genderVal = "",
                    statusVal = "",
                    onClick = {},

                    listState = LazyGridState(),
                    applyFilter = {},
                    changeGender = {},
                    changeStatus = {}
                )
            }
        }
        composeTestRule.onNodeWithText("Characters").assertIsDisplayed()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun character_screen_data_getting_displayed() {
        composeTestRule.setContent {
            RickAndMortyTheme() {
                Characters(
                    state = teststate,
                    genderVal = "",
                    statusVal = "",
                    onClick = {},

                    listState = LazyGridState(),
                    applyFilter = {},
                    changeGender = {},
                    changeStatus = {}
                )
            }
        }
        composeTestRule.onNodeWithText("Rick").assertIsDisplayed()
        composeTestRule.onNodeWithText("Ann").assertIsDisplayed()
        composeTestRule.onNodeWithText("Morty").assertIsDisplayed()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun character_screen_filter_displayed_after_click() {
        composeTestRule.setContent {
            RickAndMortyTheme() {
                Characters(
                    state = teststate,
                    genderVal = "",
                    statusVal = "",
                    onClick = {},

                    listState = LazyGridState(),
                    applyFilter = {},
                    changeGender = {},
                    changeStatus = {}
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("Filter").performClick()
        composeTestRule.onNodeWithText("Gender").assertIsDisplayed()
        composeTestRule.onNodeWithText("Click to Apply").assertIsDisplayed()
    }
}