package com.example.rickandmorty

import androidx.compose.runtime.getValue
import androidx.compose.ui.test.ExperimentalTestApi
import org.junit.Rule
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.rickandmorty.ui.screens.character.*
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithOneImage
import com.example.rickandmorty.ui.screens.episode.*
import com.example.rickandmorty.ui.screens.location.*
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@HiltAndroidTest
@ExperimentalTestApi
class LocationTest {

    @get: Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun myTest() {
        hiltRule.inject()
        var id: String = ""
        composeTestRule.setContent {
//            composeTestRule.mainClock.autoAdvance=true
            RickAndMortyTheme {
//                LocationScreen(locationsUiState = LocationViewModel.LocationUiState(), onClick = {})
//                val plusButton = composeTestRule.onNode(hasText("Location"))
//                plusButton.performClick()

                GetRowWithOneImage(
                    imageUrlLink = "",
                    titleName = "Title",
                    property1 = "Property1",
                    property2 = "Property2",
                    status = "Status",
                    id = "id",
                    onClickable = { id = it }
                )

                Thread.sleep(5000)
//                assertEquals(id, "")
//                runTest {
//                    composeTestRule.onNode(hasText("Title"))
//                        .performClick()
//
//                }

//                RickAndMortyMainApp()
            }

            assertEquals(id, "id")
        }
    }
}