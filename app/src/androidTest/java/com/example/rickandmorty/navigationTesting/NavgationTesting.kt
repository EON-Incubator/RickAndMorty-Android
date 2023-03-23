package com.example.rickandmorty.navigationTesting
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
class NavgationTesting {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun clickToGoOnCharactersScreen() = runTest {
//        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithContentDescription("Characters").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Rick Sanchez").performClick()
//
//        composeTestRule.waitUntil(5000) {
// //            composeTestRule
// //                .onNodeWithContentDescription("Row").fetchSemanticsNode()
//        }

        //    Thread.sleep(5000)
//        composeTestRule.waitUntil(5000) {
//            composeTestRule
//                .onAllNodesWithContentDescription("characters")
//                .fetchSemanticsNodes().isNotEmpty()
//        }

        //  Thread.sleep(5000)

//    }
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun clickToGoOnEpisodesScreen()= runTest{
//
//        composeTestRule.onNodeWithContentDescription("Episodes").performClick()
// //        Thread.sleep(5000)
// //        composeTestRule.waitUntil(10000) {
// //            composeTestRule
// //                .onAllNodesWithContentDescription("epi")
// //                .fetchSemanticsNodes().isNotEmpty()
// //        }
//
//
//
//
//
//    //composeTestRule.waitForIdle(10000)
// //        composeTestRule.setIdleTimeout(1000)
// //        composeTestRule.onAllNodesWithContentDescription("episodes").isEmpty()
//    }
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun clickToGoOnLocationsScreen()= runTest{
//
//        composeTestRule.onNodeWithContentDescription("Locations").performClick()
//        Thread.sleep(5000)
//        composeTestRule.waitUntil(5000) {
//            composeTestRule
//                .onAllNodesWithContentDescription("locations")
//                .fetchSemanticsNodes().isNotEmpty()
//        }
//
//
//    }
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun clickToGoOnSearchScreen()= runTest{
//
//        composeTestRule.onNodeWithContentDescription("Search").performClick()
//        Thread.sleep(5000)
//        composeTestRule.waitUntil(5000) {
//            composeTestRule
//                .onAllNodesWithContentDescription("Search Bar")
//                .fetchSemanticsNodes().isNotEmpty()
//        }
//
//
//    }
    }
}