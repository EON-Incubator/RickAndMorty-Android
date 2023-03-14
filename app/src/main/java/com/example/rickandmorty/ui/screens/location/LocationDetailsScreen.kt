package com.example.rickandmorty.ui.screens.location

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.commonUtils.GetInfoInLine

object LocationDetailsDestination : NavigationDestination {
    override val route = "location_details"
    override val screenTitleRes = R.string.location_detail_screen_title
}

@Composable
fun LocationDetailScreen(
    locationsDetailUiState: LocationDetailViewModel.LocationDetailUiState,
) {
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

        locationsDetailUiState.locationDetail.type?.let {
            GetInfoInLine(
                ImageVector.vectorResource(id = R.drawable.sort),
                "Type",
                it
            )
        }

        locationsDetailUiState.locationDetail.dimension?.let {
            GetInfoInLine(
                ImageVector.vectorResource(id = R.drawable.sort),
                "Dimension",
                it
            )
        }

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