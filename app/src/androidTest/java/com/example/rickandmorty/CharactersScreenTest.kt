//package com.example.rickandmorty
//
//import androidx.compose.foundation.lazy.grid.rememberLazyGridState
//import androidx.compose.ui.test.assertIsDisplayed
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.test.onNodeWithText
//import com.example.rickandmorty.domain.Paginate
//import com.example.rickandmorty.ui.screens.character.CharacterViewModel
//import com.example.rickandmorty.ui.screens.character.Characters
//import org.junit.Rule
//import org.junit.Test
//
//class CharactersScreenTest {
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Test
//    fun chracterScreenTest() {
//        // Start the app
//        composeTestRule.setContent {
//            val state = CharacterViewModel(
//                characters = listOf(),
//                null,
//                false,
//                selectedCharacter = null,
//                Paginate(
//                    3,
//                    10,
//                    1,
//                    20
//
//                )
//            )
//            Characters(
//                state = state,
//                genderVal = "",
//                statusVal = "",
//                onClick = {},
//                onCharacterClick = {},
//                listState = rememberLazyGridState(),
//                selectGender = {},
//                changeStatus = {},
//                changeGender = {}
//            )
//        }
//        Thread.sleep(6000)
//        // Log the full semantics tree
//        composeTestRule.onNodeWithText("Characters").assertIsDisplayed()
//    }
//}