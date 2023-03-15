package com.example.rickandmorty.ui.screens.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.character.DetailedCharacter
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.RickAndMortyTopAppBar

@Composable
fun CharacterDetails(
    modifier: Modifier = Modifier,
    state: DetailedCharacterViewModel.detailedcharacterState,
    navigateUp: () -> Unit,
) {
    Scaffold(topBar = {
        RickAndMortyTopAppBar(
            title = state.character?.name.toString(),
            canNavigateBack = true,
            navigateUp = navigateUp
        )
    }) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                // .align(Alignment.Center)
            )
        }
        // Text(text = charInfo?.ID.toString())
        else {
            DetailedScreen(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it),
                charInfo = state.character
            )
        }

//    GetInfoInLine(ImageVector.vectorResource( R.drawable.person_image), topic = "Gender", topicAnswer ="Male" )
        //  AsyncImage(model = state.character?.image.toString(), contentDescription = null)
// Text(text = "hello")
    }
}

object CharacterDetailsDestination : NavigationDestination {
    override val route = "character_detail"
    override val screenTitleRes = R.string.character_detail_screen_title
}

@Composable
fun DetailedScreen(modifier: Modifier = Modifier, charInfo: DetailedCharacter?) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(

            text = charInfo?.name.toString(),
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "APPEARANCE",
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp),
            textAlign = TextAlign.Start
        )
        AsyncImage(
            model = charInfo?.image.toString(),
            contentDescription = null,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(140.dp)
                )
                .size(280.dp),
            alignment = Alignment.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "INFO",
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, bottom = 12.dp),
            textAlign = TextAlign.Start
        )
        Divider(color = Color.Black, thickness = 2.dp)
        Column {
            GetInfoInLine(
                icons = ImageVector.vectorResource(R.drawable.man_fill0_wght400_grad0_opsz48),
                topic = "Gender",
                topicAnswer = charInfo?.gender.toString()
            )
            GetInfoInLine(
                icons = ImageVector.vectorResource(R.drawable.category_fill0_wght400_grad0_opsz48),
                topic = "Species",
                topicAnswer = charInfo?.species.toString()
            )
            GetInfoInLine(
                icons = ImageVector.vectorResource(R.drawable.deceased_fill0_wght400_grad0_opsz48),
                topic = "Status",
                topicAnswer = charInfo?.status.toString()
            )
            Text(
                text = "LOCATION",
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 12.dp, top = 12.dp),
                textAlign = TextAlign.Start
            ) // Spacer(modifier = Modifier.height(20.dp))
            Divider(thickness = 2.dp)
            GetInfoInLine(
                icons = ImageVector
                    .vectorResource(R.drawable.trip_origin_fill0_wght400_grad0_opsz48),
                topic = "Origin",
                topicAnswer = charInfo?.dimension.toString()
            )
            GetInfoInLine(
                icons = ImageVector.vectorResource(R.drawable.explore_fill0_wght400_grad0_opsz48),
                topic = "Last Seen",
                topicAnswer = charInfo?.created.toString()
            )
            Text(text = "EPISODES", style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun GetInfoInLine(
    icons: ImageVector,
    topic: String,
    topicAnswer: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, bottom = 5.dp, top = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(imageVector = icons, contentDescription = null)
        Text(
            modifier = Modifier.weight(2f),
            text = topic,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.weight(1f),
            text = topicAnswer,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Normal
        )
    }
    Divider(
        Modifier.height(1.dp),
        color = MaterialTheme.colors.onBackground
    )
}
// @Preview(showSystemUi = true)
//
// @Composable
// fun showup() {
//    DetailedScreen()
// }