package com.example.rickandmorty

import androidx.compose.runtime.*
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
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

//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun searchFunctionality() = runTest {
//        composeTestRule.onNodeWithContentDescription("Search").performClick()
//        composeTestRule.waitForIdle()
//        composeTestRule.onNodeWithContentDescription("Search Bar").performTextInput("Rick")
//        composeTestRule.onNodeWithContentDescription("Search Bar").assert(hasText("Rick"))
//        composeTestRule.waitForIdle()
//        composeTestRule.waitUntil(10000) {
//            composeTestRule
//                .onAllNodesWithContentDescription("Fetching Records")
//                .fetchSemanticsNodes().isEmpty()
//        }
//        composeTestRule.onAllNodesWithContentDescription("Single Image Row").assertAny(
//            hasContentDescription("Item Name")
//        )
//        composeTestRule.onNodeWithContentDescription("Load More Characters").performClick()
//        composeTestRule.waitForIdle()
//        composeTestRule.onNodeWithContentDescription("Search Bar").performTextInput("Planet")
//        composeTestRule.onNodeWithContentDescription("Search Bar").assert(hasText("Planet"))
//        composeTestRule.waitForIdle()
//        composeTestRule.waitUntil(10000) {
//            composeTestRule
//                .onAllNodesWithContentDescription("Fetching Records")
//                .fetchSemanticsNodes().isEmpty()
//        }
//        composeTestRule.onNodeWithContentDescription("Load More Locations").performClick()
//        composeTestRule.waitForIdle()
//        composeTestRule.onNodeWithText("Rick Sanchez").performClick()
//    }

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
        Thread.sleep(1000)

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Pilot").performClick()
        composeTestRule.waitUntil(2000) {
            composeTestRule
                .onAllNodesWithContentDescription("EP Detail")
                .fetchSemanticsNodes().isNotEmpty()
        }
        Thread.sleep(1000)

        composeTestRule.waitForIdle()
        composeTestRule.onAllNodesWithContentDescription("Single Image Row").assertAny(
            hasContentDescription("Item Name")
        )

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Rick Sanchez").performClick()
        composeTestRule.waitForIdle()

        Thread.sleep(1000)
    }
}