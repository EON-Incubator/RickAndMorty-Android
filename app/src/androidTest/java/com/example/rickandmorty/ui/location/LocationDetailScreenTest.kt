package com.example.rickandmorty.ui.location

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.domain.location.LocationDetail
import com.example.rickandmorty.ui.screens.ScreenType
import com.example.rickandmorty.ui.screens.location.LocationDetailScreen
import com.example.rickandmorty.ui.screens.location.LocationDetailViewModel
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LocationDetailScreenTest {

    var viewModel: LocationDetailViewModel.LocationDetailUiState = LocationDetailViewModel.LocationDetailUiState()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        viewModel = LocationDetailViewModel.LocationDetailUiState(
            locationDetail = LocationDetail(
                "dimension1",
                "name1",
                listOf(
                    DetailedCharacter(
                        "1",
                        "Resident Name",
                        "image",
                        "species",
                        "Dead",
                        "Male",
                        emptyList(),
                        "lastSeen",
                        "lastSeenId",
                        "originId",
                        "origin"
                    )
                ),
                "type1"
            ),
            isLoading = false
        )
    }

    @Test
    fun locationDetailScreen_scaffold_check() {
        composeTestRule.setContent {
            RickAndMortyTheme() {
                LocationDetailScreen(
                    locationsDetailUiState = viewModel,
                    navigateUp = {},
                    onCharacterClick = {},
                    ScreenType.PORTRAIT_PHONE

                )
            }
        }

        composeTestRule.onNodeWithText("name1").assertIsDisplayed()
    }

    @Test
    fun locationDetailScreen_properties() {
        composeTestRule.setContent {
            RickAndMortyTheme() {
                LocationDetailScreen(
                    locationsDetailUiState = viewModel,
                    navigateUp = {},
                    onCharacterClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText("dimension1").assertIsDisplayed()
        composeTestRule.onNodeWithText("type1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Resident Name").assertIsDisplayed()
    }

    @Test
    fun locationDetailScreen_residents_check() {
        var id = "id"
        composeTestRule.setContent {
            RickAndMortyTheme() {
                LocationDetailScreen(
                    locationsDetailUiState = viewModel,
                    navigateUp = {},
                    onCharacterClick = { id = it }
                )
            }
        }

        composeTestRule.onNodeWithText("Resident Name").performClick()
        Assert.assertEquals(id, "1")
    }

    @Test
    fun locationDetailScreen_navigate_check() {
        var nav = false
        composeTestRule.setContent {
            RickAndMortyTheme() {
                LocationDetailScreen(
                    locationsDetailUiState = viewModel,
                    navigateUp = { nav = true },
                    onCharacterClick = {}
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("Back Button").performClick()
        assert(nav)
    }
}