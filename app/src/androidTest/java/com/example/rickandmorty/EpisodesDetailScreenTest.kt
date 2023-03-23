import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
// import androidx.compose.ui.test.junit4.onNodeWithText
// import androidx.compose.ui.test.junit4.onNodeWithTag
import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.ui.screens.episode.EpisodeDetailViewModel
import com.example.rickandmorty.ui.screens.episode.EpisodeDetails
import org.junit.Rule
import org.junit.Test

class EpisodesDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun episodesDetailScreenTest() {
        // Set up the viewmodel with some test data.
        val viewModel = EpisodeDetailViewModel.DetailEpisodesState(
            characters = listOf(),
            isLoading = false,
            selectedEpisode = DetailedEpisode(
                "1",
                "Pilot",
                "S01E01",
                "2021-01-01",
                listOf(
                    Character("1", "Rick", "url_1", "Human", "Alive", "Male"),
                    Character(ID = "2", "Minty", "url_2", "Vampire", "Dead", "Female")
                )
            )
        )

        // Set up the composable with the viewmodel.
        composeTestRule.setContent {
            EpisodeDetails(state = viewModel, navigateUp = { }, onCharacterClick = { })
        }

        // Delays the screen for 5 seconds to show the data passed.
        Thread.sleep(5000)

        // Verify that the screen title is displayed.
        composeTestRule.onNodeWithText("Pilot").assertIsDisplayed()

        // Verify that the episode info is displayed.
        composeTestRule.onNodeWithText("S01E01").assertIsDisplayed()
        composeTestRule.onNodeWithText("2021-01-01").assertIsDisplayed()

        // Verify that the list of characters are displayed.
        composeTestRule.onNodeWithText("Alive").assertIsDisplayed()
        composeTestRule.onNodeWithText("Rick").assertIsDisplayed()
        composeTestRule.onNodeWithText("Male").assertIsDisplayed()
        composeTestRule.onNodeWithText("Human").assertIsDisplayed()
        composeTestRule.onNodeWithText("Dead").assertIsDisplayed()
        composeTestRule.onNodeWithText("Minty").assertIsDisplayed()
        composeTestRule.onNodeWithText("Female").assertIsDisplayed()
        composeTestRule.onNodeWithText("Vampire").assertIsDisplayed()
    }
}