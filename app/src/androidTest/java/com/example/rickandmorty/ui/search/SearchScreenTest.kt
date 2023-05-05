//package com.example.rickandmorty.ui.search
//
//import androidx.compose.foundation.lazy.LazyListState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.test.*
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.text.input.TextFieldValue
//import com.example.rickandmorty.domain.Paginate
//import com.example.rickandmorty.domain.character.Character
//import com.example.rickandmorty.domain.character.CharacterData
//import com.example.rickandmorty.domain.location.Location
//import com.example.rickandmorty.domain.location.LocationData
//import com.example.rickandmorty.ui.screens.ScreenType
//import com.example.rickandmorty.ui.screens.search.Search
//import com.example.rickandmorty.ui.screens.search.SearchViewModel
//import com.example.rickandmorty.ui.theme.RickAndMortyTheme
//import org.junit.Assert
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
//class SearchScreenTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    var searchResultSate: SearchViewModel.SearchResult = SearchViewModel.SearchResult()
//
//    lateinit var onCharacterClick: String
//    lateinit var onLocationClick: String
//    lateinit var onValueChange: String
//    lateinit var onShowCharacters: String
//    lateinit var onShowLocations: String
//
//    @Before
//    fun setUp() {
//        searchResultSate = SearchViewModel.SearchResult(
//            characterData = CharacterData(
//                pages = Paginate(
//                    next = null,
//                    prev = null,
//                    pages = 1,
//                    count = 1
//                ),
//                characters = listOf(
//                    Character(
//                        ID = "1",
//                        name = "Rick",
//                        image = "",
//                        status = "Alive",
//                        species = "Human",
//                        gender = "Male"
//                    ),
//                    Character(
//                        ID = "2",
//                        name = "Rick2",
//                        image = "",
//                        status = "Alive2",
//                        species = "Human2",
//                        gender = "Male2"
//                    ),
//                    Character(
//                        ID = "3",
//                        name = "Rick3",
//                        image = "",
//                        status = "Alive3",
//                        species = "Human3",
//                        gender = "Male3"
//                    )
//                )
//            ),
//            locationByName = LocationData(
//                pages = Paginate(
//                    next = null,
//                    prev = null,
//                    pages = 1,
//                    count = 1
//                ),
//                locations = listOf(
//                    Location(
//                        id = "1",
//                        name = "Earth",
//                        type = "Planet",
//                        dimension = "Unknown",
//                        images = emptyList(),
//                        created = ""
//                    ),
//                    Location(
//                        id = "2",
//                        name = "Earth2",
//                        type = "Planet2",
//                        dimension = "Unknown2",
//                        images = emptyList(),
//                        created = ""
//                    )
//                )
//            ),
//            locationByType = LocationData(
//                pages = Paginate(
//                    next = null,
//                    prev = null,
//                    pages = 1,
//                    count = 1
//                ),
//                locations = listOf(
//                    Location(
//                        id = "1",
//                        name = "Earth3",
//                        type = "Planet",
//                        dimension = "Unknown",
//                        images = emptyList(),
//                        created = ""
//                    )
//                )
//            ),
//            isLoading = false
//
//        )
//    }
//
//    @Test
//    fun search_screen_character_and_location_properties() {
//        composeTestRule.setContent {
//            RickAndMortyTheme() {
//                Search(
//                    searchResultState = searchResultSate,
//                    onCharacterClick = {},
//                    onLocationClick = {},
//                    onValueChange = {},
//                    onShowCharacters = {},
//                    onShowLocations = {},
//                    query = remember { mutableStateOf(TextFieldValue("")) },
//                    showCharacters = true,
//                    showLocations = true,
//                    updateLocationList = {},
//                    updateCharacterList = {},
//                    searchListState = LazyListState(),
//                    deviceType = ScreenType.PORTRAIT_PHONE,
//                    onResetQuery = {}
//                )
//            }
//        }
//
//        composeTestRule.onNodeWithText("Rick").assertIsDisplayed()
//        composeTestRule.onNodeWithText("Alive").assertIsDisplayed()
//        composeTestRule.onNodeWithText("Human").assertIsDisplayed()
//        composeTestRule.onNodeWithText("Male").assertIsDisplayed()
//        composeTestRule.onNodeWithText("Earth").assertIsDisplayed()
//        composeTestRule.onNodeWithText("Planet").assertIsDisplayed()
//        composeTestRule.onNodeWithText("Unknown").assertIsDisplayed()
//    }
//
//    @Test
//    fun search_screen_onClick_check() {
//        composeTestRule.setContent {
//            RickAndMortyTheme() {
//                Search(
//                    searchResultState = searchResultSate,
//                    onCharacterClick = { onCharacterClick = it },
//                    onLocationClick = { onLocationClick = it },
//                    onValueChange = { onValueChange = it },
//                    onShowCharacters = { onShowCharacters = "true" },
//                    onShowLocations = { onShowLocations = "true" },
//                    query = remember { mutableStateOf(TextFieldValue("")) },
//                    showCharacters = true,
//                    showLocations = true,
//                    updateLocationList = {},
//                    updateCharacterList = {},
//                    searchListState = LazyListState(),
//                    deviceType = ScreenType.PORTRAIT_PHONE,
//                    onResetQuery = {}
//                )
//            }
//        }
//        composeTestRule.onNodeWithText("Rick").performClick()
//        Assert.assertEquals(onCharacterClick, "1")
//        composeTestRule.onNodeWithText("Earth").performClick()
//        Assert.assertEquals(onLocationClick, "1")
//        composeTestRule.onNodeWithTag("Characters").performClick()
//        Assert.assertEquals(onShowCharacters, "true")
//        composeTestRule.onNodeWithTag("Locations").performClick()
//        Assert.assertEquals(onShowLocations, "true")
//        composeTestRule.onNodeWithTag("Search Bar").performTextInput("Rick")
//        Assert.assertEquals(onValueChange, "Rick")
//    }
//}