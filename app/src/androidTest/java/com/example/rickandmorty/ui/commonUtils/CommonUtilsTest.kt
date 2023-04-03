package com.example.rickandmorty.ui.commonUtils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.screens.commonUtils.TopBar
import com.example.rickandmorty.ui.screens.commonUtils.GetInfoInLine
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithFourImages
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithOneImage
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
class CommonUtilsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun given_title_should_show_name_in_top_bar() {
        composeTestRule.setContent {
            RickAndMortyTheme() {
                TopBar(title = "Title")
            }
        }
        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
    }

    @Test
    fun getRowWithOneImage_should_display_text_properties() {
        composeTestRule.setContent {
            RickAndMortyTheme() {
                GetRowWithOneImage(
                    imageUrlLink = "Link",
                    titleName = "Title",
                    property1 = "Property1",
                    property2 = "Property2",
                    status = "Status",
                    id = "id",
                    onClickable = {}
                )
            }
        }
        composeTestRule.onNodeWithText("Property2").assertIsDisplayed()
    }

    @Test
    fun getRowWithOneImage_should_have_icon_displayed() {
        composeTestRule.setContent {
            RickAndMortyTheme() {
                GetRowWithOneImage(
                    imageUrlLink = "Link",
                    titleName = "Title",
                    property1 = "Property1",
                    property2 = "Property2",
                    status = "Status",
                    id = "id",
                    onClickable = {}
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("Icon of Location Characters")
            .assertIsDisplayed()
    }

    @Test
    fun getRowWithOneImage_should_name_depending_Status() {
        composeTestRule.setContent {
            RickAndMortyTheme() {
                Column() {
                    GetRowWithOneImage(
                        imageUrlLink = "Link",
                        titleName = "Title",
                        property1 = "Property1",
                        property2 = "Property2",
                        status = "Dead",
                        id = "id",
                        onClickable = {}
                    )

                    GetRowWithOneImage(
                        imageUrlLink = "Link",
                        titleName = "Title",
                        property1 = "Property1",
                        property2 = "Property2",
                        status = "Alive",
                        id = "id",
                        onClickable = {}
                    )
                }
            }
        }
        composeTestRule.onNodeWithText("Dead").assertIsDisplayed()

        composeTestRule.onNodeWithText("Alive").assertIsDisplayed()
    }

    @Test
    fun getRowWithOneImage_should_be_cliclable() {
        var id = "id"
        composeTestRule.setContent {
            RickAndMortyTheme() {
                GetRowWithOneImage(
                    imageUrlLink = "Link",
                    titleName = "Title",
                    property1 = "Property1",
                    property2 = "Property2",
                    status = "Status",
                    id = "id1",
                    onClickable = { id = it }
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("Icon of Location Characters").performClick()
        Assert.assertNotEquals(id, "id")
    }

    @Test
    fun getRowWithFourImages_should_display_text_properties() {
        composeTestRule.setContent {
            GetRowWithFourImages(
                imageUrlLink = emptyList(),
                titleName = "Title",
                property1 = "Property1",
                property2 = "Property2",
                onClickable = {},
                id = "id"
            )
        }
        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Property1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Property2").assertIsDisplayed()
    }

    @Test
    fun getRowWithFourImages_should_display_run_when_no_image_passed() {
        composeTestRule.setContent {
            GetRowWithFourImages(
                imageUrlLink = emptyList(),
                titleName = "Title",
                property1 = "Property1",
                property2 = "Property2",
                onClickable = {},
                id = "id"
            )
        }
        composeTestRule.onNodeWithContentDescription("Icon of Location Characters")
            .assertIsDisplayed()
    }

    @Test
    fun getInfoInLine_should_display_properties() {
        composeTestRule.setContent {
            RickAndMortyTheme() {
                GetInfoInLine(
                    icons = ImageVector.vectorResource(id = R.drawable.sort),
                    topic = "Topic",
                    topicAnswer = "TopicAnswer"
                )
            }
        }
        composeTestRule.onNodeWithText("Topic").assertIsDisplayed()
        composeTestRule.onNodeWithText("TopicAnswer").assertIsDisplayed()
    }
}