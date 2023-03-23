package com.example.rickandmorty.navigationTesting

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rickandmorty.ui.screens.RickAndMortyMainApp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NavgationTesting {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Test
    fun initialSetup() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            RickAndMortyMainApp(navController = navController)
        }
//        Log.v("name",navController.currentBackStackEntry?.destination?.route.toString())
//        Log.v("tg",CharacterDestination.route.toString())
    }

    @Test
    fun rickAndMoryNavHost_verifyStartDestination() {
//        val viewModel: MyViewModel = composeTestRule.activity
//            .viewModel(HiltViewModelFactory(composeTestRule.activity, null))
//
//        composeTestRule.setContent {
//            Characters(state = , onClick = {}, onCharacterClick ={} , listState = LazyGridState() )
//        }
//        val navigateButton= composeTestRule.onNodeWithText("Characters")
//        navigateButton.performClick()
//
//        composeTestRule.onNodeWithText("Characters").assertExists()
        assertEquals(null, navController.currentBackStackEntry?.destination?.route)
    }
}