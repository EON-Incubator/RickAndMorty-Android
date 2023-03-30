package com.example.rickandmorty.ui.episode

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.rickandmorty.domain.Episodes
import com.example.rickandmorty.domain.Paginate
import com.example.rickandmorty.ui.screens.ScreenType
import com.example.rickandmorty.ui.screens.episode.EpisodeViewModel
import com.example.rickandmorty.ui.screens.episode.EpisodesScreen
import org.junit.Rule
import org.junit.Test

class EpisodesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun episodesScreenTest() {
        // Set up the viewmodel with some test data.
        val viewModel = EpisodeViewModel.EpisodesState(
            episodes = listOf(
                Episodes(
                    "1",
                    "Episode 1",
                    "S01E01",
                    "2021-01-01",
                    listOf("image_url_1", "image_url_2")
                ),
                Episodes(
                    "2",
                    "Episode 2",
                    "S01E02",
                    "2021-01-02",
                    listOf("image_url_3", "image_url_4")
                )
            ),
            isLoading = false,
            pages = Paginate(1, 2, 0, 20)
        )

        // Set up the composable with the viewmodel.
        composeTestRule.setContent {
            EpisodesScreen(
                state = viewModel,
                onSelectEpisode = { },
                listState = LazyGridState(),
                deviceType = ScreenType.PORTRAIT_PHONE
            )
        }

        // Delays the screen for 5 seconds to show the data passed.
        Thread.sleep(5000)

        // Verify that the screen title is displayed.
        composeTestRule.onNodeWithText("Episodes").assertIsDisplayed()

        // Verify that the list of items are displayed.
        composeTestRule.onNodeWithText("Episode 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("S01E01").assertIsDisplayed()
        composeTestRule.onNodeWithText("2021-01-01").assertIsDisplayed()
        composeTestRule.onNodeWithText("Episode 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("S01E02").assertIsDisplayed()
        composeTestRule.onNodeWithText("2021-01-02").assertIsDisplayed()
    }

    @Test
    fun episodesScreenTestLandscape() {
        // Set up the viewmodel with some test data.
        val viewModel = EpisodeViewModel.EpisodesState(
            episodes = listOf(
                Episodes(
                    "1",
                    "Episode 1",
                    "S01E01",
                    "2021-01-01",
                    listOf("image_url_1", "image_url_2")
                ),
                Episodes(
                    "2",
                    "Episode 2",
                    "S01E02",
                    "2021-01-02",
                    listOf("image_url_3", "image_url_4")
                )
            ),
            isLoading = false,
            pages = Paginate(1, 2, 0, 20)
        )

        // Set up the composable with the viewmodel.
        composeTestRule.setContent {
            EpisodesScreen(
                state = viewModel,
                onSelectEpisode = { },
                listState = LazyGridState(),
                deviceType = ScreenType.LANDSCAPE_PHONE
            )
        }

        // Delays the screen for 5 seconds to show the data passed.
        Thread.sleep(5000)

        // Verify that the list of items are displayed.
        composeTestRule.onNodeWithText("Episode 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("S01E01").assertIsDisplayed()
        composeTestRule.onNodeWithText("2021-01-01").assertIsDisplayed()
        composeTestRule.onNodeWithText("Episode 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("S01E02").assertIsDisplayed()
        composeTestRule.onNodeWithText("2021-01-02").assertIsDisplayed()
    }

    @Test
    fun episodesScreenTestTablet() {
        // Set up the viewmodel with some test data.
        val viewModel = EpisodeViewModel.EpisodesState(
            episodes = listOf(
                Episodes(
                    "1",
                    "Episode 1",
                    "S01E01",
                    "2021-01-01",
                    listOf("image_url_1", "image_url_2")
                ),
                Episodes(
                    "2",
                    "Episode 2",
                    "S01E02",
                    "2021-01-02",
                    listOf("image_url_3", "image_url_4")
                )
            ),
            isLoading = false,
            pages = Paginate(1, 2, 0, 20)
        )

        // Set up the composable with the viewmodel.
        composeTestRule.setContent {
            EpisodesScreen(
                state = viewModel,
                onSelectEpisode = { },
                listState = LazyGridState(),
                deviceType = ScreenType.LANDSCAPE_TABLET
            )
        }

        // Delays the screen for 5 seconds to show the data passed.
        Thread.sleep(5000)

        // Verify that the list of items are displayed.
        composeTestRule.onNodeWithText("Episode 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("S01E01").assertIsDisplayed()
        composeTestRule.onNodeWithText("2021-01-01").assertIsDisplayed()
        composeTestRule.onNodeWithText("Episode 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("S01E02").assertIsDisplayed()
        composeTestRule.onNodeWithText("2021-01-02").assertIsDisplayed()
    }
}