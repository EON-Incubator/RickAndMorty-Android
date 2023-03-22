package com.example.rickandmorty

import androidx.compose.runtime.*
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.rickandmorty.ui.screens.search.SearchViewModel
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
        composeTestRule.onNodeWithContentDescription("Search Bar").performTextInput("Rick")
        composeTestRule.onNodeWithContentDescription("Search Bar").assert(hasText("Rick"))
        composeTestRule.waitForIdle()
        composeTestRule.waitUntil(10000) {
            composeTestRule
                .onAllNodesWithContentDescription("Fetching Records")
                .fetchSemanticsNodes().isEmpty()
        }
        composeTestRule.onAllNodesWithContentDescription("Single Image Row").assertAny(
            hasContentDescription("Item Name")
        )
    }
}