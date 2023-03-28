package com.example.rickandmorty.ui.character

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.rickandmorty.domain.Episodes
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.ui.screens.character.CharacterDetails
import com.example.rickandmorty.ui.screens.character.DetailedCharacterViewModel
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailsScreenTest() {
    @get:Rule
    var composeTestRule = createComposeRule()
    lateinit var testState: DetailedCharacterViewModel.detailedcharacterState

    @Before
    fun setup() {
        testState =
            DetailedCharacterViewModel.detailedcharacterState(
                DetailedCharacter(
                    "ID1",
                    "Rick",
                    "https://rickandmortyapi.com/api/character/avatar/5.jpeg",
                    "organic",
                    "Alive",
                    "male",
                    listOf<Episodes>(),
                    "ludhiana",
                    "l1",
                    "O1",
                    "Earth"
                ),
                false
            )
    }

    @Test
    fun detailed_character_screen_name_top_bar_is_characters() {
        composeTestRule.setContent {
            RickAndMortyTheme() {
                CharacterDetails(
                    state = testState,
                    navigateUp = { /*TODO*/ },
                    onEpisodeClick = {},
                    onOriginClick = {},
                    onLastSeenClick = {}
                )
            }
        }
        composeTestRule.onNodeWithText("Rick").assertIsDisplayed()
        Thread.sleep(6000)
    }

    @Test
    fun detailed_character_screen_data_getting_displayed() {
        composeTestRule.setContent {
            RickAndMortyTheme() {
                CharacterDetails(
                    state = testState,
                    navigateUp = { /*TODO*/ },
                    onEpisodeClick = {},
                    onOriginClick = {},
                    onLastSeenClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Gender").assertIsDisplayed()
        composeTestRule.onNodeWithText("Rick").assertIsDisplayed()
        composeTestRule.onNodeWithText("ludhiana").assertIsDisplayed()
        composeTestRule.onNodeWithText("Earth").assertIsDisplayed()
        composeTestRule.onNodeWithText("Gender").assertIsDisplayed()

        Thread.sleep(6000)
    }

    @Test
    fun sacffold_navigation_testing() {
        var navstate = false
        composeTestRule.setContent {
            RickAndMortyTheme() {
                CharacterDetails(
                    state = testState,
                    navigateUp = { navstate = true },
                    onEpisodeClick = {},
                    onOriginClick = {},
                    onLastSeenClick = {}
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("Back Button").performClick()
        assert(navstate)
        Thread.sleep(5000)
    }
}