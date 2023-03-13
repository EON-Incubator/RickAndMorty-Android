package com.example.rickandmorty.ui.screens.location

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination

object LocationDetailsDestination : NavigationDestination {
    override val route = "location_details"
    override val screenTitleRes = R.string.location_detail_screen_title
}

@Composable
fun LocationDetailScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "INFO",
            modifier = Modifier.padding(start = 10.dp, top = 20.dp, bottom = 8.dp),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Normal
        )

        Divider(
            Modifier.height(1.dp),
            color = MaterialTheme.colors.onBackground
        )

        GetInfo("Type", "Microverse")
        GetInfo("Dimention", "Dimention C-137")

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "RESIDENTS",
            modifier = Modifier.padding(start = 10.dp, top = 20.dp, bottom = 8.dp),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun GetInfo(topic: String, topicAnswer: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, bottom = 5.dp, top = 5.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
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