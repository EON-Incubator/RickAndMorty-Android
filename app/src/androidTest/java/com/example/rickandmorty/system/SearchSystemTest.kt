package com.example.rickandmorty.system

import androidx.compose.runtime.*
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.rickandmorty.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SearchSystemTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun searchFunctionality() = runTest {
        composeTestRule.onNodeWithContentDescription("Search").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("Search Bar").performTextInput("Rick")
        composeTestRule.onNodeWithTag("Search Bar").assert(hasText("Rick"))
        composeTestRule.waitForIdle()
        composeTestRule.waitUntil(10000) {
            composeTestRule
                .onAllNodesWithTag("Fetching Records")
                .fetchSemanticsNodes().isEmpty()
        }
        Thread.sleep(3000)
        composeTestRule.onAllNodesWithTag("Single Image Row").assertAny(
            hasContentDescription("Item Name")
        )
        composeTestRule.onNodeWithTag("search_lazy_column")
            .performScrollToNode(hasContentDescription("Load More Characters"))
        composeTestRule.onNodeWithContentDescription("Load More Characters").performScrollTo()
            .performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("Search Bar").performTextInput("Planet")
        composeTestRule.onNodeWithTag("Search Bar").assert(hasText("Planet"))
        composeTestRule.waitForIdle()
        composeTestRule.waitUntil(10000) {
            composeTestRule
                .onAllNodesWithTag("Fetching Records")
                .fetchSemanticsNodes().isEmpty()
        }
        Thread.sleep(3000)
        composeTestRule.onNodeWithTag("search_lazy_column")
            .performScrollToNode(hasContentDescription("Load More Locations"))
        composeTestRule.onNodeWithContentDescription("Load More Locations").performScrollTo()
            .performClick()
        composeTestRule.waitForIdle()
        composeTestRule.waitUntil(10000) {
            composeTestRule
                .onAllNodesWithContentDescription("Fetching Characters")
                .fetchSemanticsNodes().isEmpty()
        }
        Thread.sleep(3000)
        composeTestRule.onNodeWithText("Dorian 5").performClick()
    }

    /**
     * BDD testing for character screen
     */

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun clickToGoOnCharactersScreen() = runTest {
        composeTestRule.waitUntil(10000) {
            composeTestRule
                .onAllNodesWithContentDescription("Fetching Characters")
                .fetchSemanticsNodes().isEmpty()
        }
        composeTestRule.onNodeWithText("Rick Sanchez").performClick()

        composeTestRule.waitForIdle()
        composeTestRule.waitUntil(10000) {
            composeTestRule
                .onAllNodesWithContentDescription("Fetching Character")
                .fetchSemanticsNodes().isEmpty()
        }
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Pilot").performClick()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun location_functionality() = runTest {
        composeTestRule.onNodeWithContentDescription("Locations").performClick()
        composeTestRule.waitUntil(10000) {
            composeTestRule
                .onAllNodesWithContentDescription("Fetching Records")
                .fetchSemanticsNodes().isEmpty()
        }
        composeTestRule.onNodeWithText("Abadango").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("INFO").assertIsDisplayed()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun episodesAndDetailFunctionality() = runTest {
        composeTestRule.onNodeWithContentDescription("Episodes").performClick()
        composeTestRule.waitUntil(2000) {
            composeTestRule
                .onAllNodesWithContentDescription("Fetching Records")
                .fetchSemanticsNodes().isEmpty()
        }

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Pilot").performClick()
        composeTestRule.waitUntil(2000) {
            composeTestRule
                .onAllNodesWithTag("EP Detail")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Rick Sanchez").performClick()
        composeTestRule.waitForIdle()
    }
}