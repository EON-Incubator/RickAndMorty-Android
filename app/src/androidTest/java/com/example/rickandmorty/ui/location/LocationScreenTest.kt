package com.example.rickandmorty.ui.location

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.domain.location.Location
import com.example.rickandmorty.ui.screens.ScreenType
import com.example.rickandmorty.ui.screens.location.LocationScreen
import com.example.rickandmorty.ui.screens.location.LocationViewModel
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
class LocationScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    var viewModel: LocationViewModel.LocationUiState = LocationViewModel.LocationUiState()
    var id: String = "id"

    @Before
    fun setUp() {
        viewModel = LocationViewModel.LocationUiState(
            locations = listOf(
                Location("1", "name1", "type1", "dim1", emptyList(), "created1"),
                Location("2", "name2", "type2", "dim2", emptyList(), "created2"),
                Location("3", "name3", "type3", "dim3", emptyList(), "created3")
            ),
            isLoading = false,
            pages = Paginate(1, 2, 0, 20)

        )
    }

    @Test
    fun screen_name_bar_result_check() {
        composeTestRule.setContent {
            RickAndMortyTheme() {
                LocationScreen(
                    locationsUiState = viewModel,
                    onClick = {},
                    listState = LazyGridState(),
                    deviceType = ScreenType.PORTRAIT_PHONE

                )
            }
        }

        composeTestRule.onNodeWithText("Locations").assertIsDisplayed()
    }

    @Test
    fun screen_name_bar_result_check_landscape() {
        composeTestRule.setContent {
            RickAndMortyTheme() {
                LocationScreen(
                    locationsUiState = viewModel,
                    onClick = {},
                    listState = LazyGridState(),
                    deviceType = ScreenType.LANDSCAPE_PHONE
                )
            }
        }

        composeTestRule.onNodeWithText("name1").assertIsDisplayed()
    }

    @Test
    fun screen_name_bar_result_check_tablet() {
        composeTestRule.setContent {
            RickAndMortyTheme() {
                LocationScreen(
                    locationsUiState = viewModel,
                    onClick = {},
                    listState = LazyGridState(),
                    deviceType = ScreenType.LANDSCAPE_TABLET

                )
            }
        }

        composeTestRule.onNodeWithText("Locations").assertIsDisplayed()
    }

    @Test
    fun screen_location_screen_properties() {
        composeTestRule.setContent {
            RickAndMortyTheme() {
                LocationScreen(
                    locationsUiState = viewModel,
                    onClick = {},
                    listState = LazyGridState(),
                    deviceType = ScreenType.PORTRAIT_PHONE

                )
            }
        }

        composeTestRule.onNodeWithText("name1").assertIsDisplayed()
        composeTestRule.onNodeWithText("name2").assertIsDisplayed()
        composeTestRule.onNodeWithText("name3").assertIsDisplayed()
        composeTestRule.onNodeWithText("type1").assertIsDisplayed()
        composeTestRule.onNodeWithText("type1").assertIsDisplayed()
        composeTestRule.onNodeWithText("type3").assertIsDisplayed()
    }

    @Test
    fun location_screen_onClick_check() {
        composeTestRule.setContent {
            RickAndMortyTheme() {
                LocationScreen(
                    locationsUiState = viewModel,
                    onClick = { id = it },
                    listState = LazyGridState(),
                    deviceType = ScreenType.PORTRAIT_PHONE

                )
            }
        }

        composeTestRule.onNodeWithText("name1").performClick()
        Assert.assertEquals(id, "1")

        composeTestRule.onNodeWithText("name2").performClick()
        Assert.assertEquals(id, "2")

        composeTestRule.onNodeWithText("name3").performClick()
        Assert.assertEquals(id, "3")
    }
}