package com.example.rickandmorty.ui.screens.location

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination
import com.example.rickandmorty.ui.screens.commonUtils.RickAndMortyTopAppBar
import com.example.rickandmorty.ui.screens.ScreenType
import com.example.rickandmorty.ui.screens.commonUtils.GetPadding
import com.example.rickandmorty.ui.screens.commonUtils.GetInfoInLine
import com.example.rickandmorty.ui.screens.commonUtils.GetRowWithOneImage

/**
 * Defining the route for the LocationScreen
 */
object LocationDetailsDestination : NavigationDestination {
    override val route = "location_details"
    override val screenTitleRes = R.string.location_detail_screen_title
}

/**
 * Composable function that draws Location Detail Screen which
 * is generated after clicking 1 location on Location Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetailScreen(
    locationsDetailUiState: LocationDetailViewModel.LocationDetailUiState,
    navigateUp: () -> Unit,
    onCharacterClick: (String) -> Unit,
    deviceType: ScreenType = ScreenType.PORTRAIT_PHONE,
) {
    // Scaffold to have a seperate Top Bar for this screen
    Scaffold(
        topBar = {
            RickAndMortyTopAppBar(
                title = locationsDetailUiState.locationDetail.name.toString(),
                canNavigateBack = true,
                navigateUp = navigateUp,
                backgroundColor = colorResource(id = R.color.locationDetail_background)
            )
        },
        backgroundColor = colorResource(id = R.color.locationDetail_background)
    ) {
        if (locationsDetailUiState.isLoading) {
            LocationDetailLoader(deviceType = deviceType)
        } else {
            if (deviceType == ScreenType.PORTRAIT_PHONE) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    GetInfo(locationsDetailUiState)
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_40)))
                    GetResidents(locationsDetailUiState, onCharacterClick)
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    GetInfo(
                        locationsDetailUiState,
                        modifier = Modifier.weight(2f)
                    )
                    GetResidents(
                        locationsDetailUiState,
                        onCharacterClick,
                        modifier = Modifier.weight(5f)
                    )
                }
            }
        }
    }
}

@Composable
fun GetResidents(
    locationsDetailUiState: LocationDetailViewModel.LocationDetailUiState,
    onCharacterClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    fixedElement: Int = 1,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.residents_all_caps),
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.padding_xlarge),
                top = GetPadding().xxxLargePadding,
                bottom = GetPadding().largePadding
            ),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Normal
        )

        LazyVerticalGrid(columns = GridCells.Fixed(fixedElement)) {
            locationsDetailUiState.locationDetail.residents?.let {
                items(locationsDetailUiState.locationDetail.residents) { resident ->
                    // Method in CommonUtils that draws a Row with 1 Images
                    GetRowWithOneImage(
                        imageUrlLink = resident.image.toString(),
                        titleName = resident.name.toString(),
                        property1 = resident.gender.toString(),
                        property2 = resident.species.toString(),
                        status = resident.status.toString(),
                        id = resident.ID.toString(),
                        onClickable = {
                            onCharacterClick(it)
                        },
                        icons = listOf(
                            ImageVector.vectorResource(id = R.drawable.man_fill0_wght400_grad0_opsz48),
                            ImageVector.vectorResource(id = R.drawable.category_fill0_wght400_grad0_opsz48)
                        )

                    )
                }
            }
        }
    }
}

@Composable
fun GetInfo(
    locationsDetailUiState: LocationDetailViewModel.LocationDetailUiState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.info),
            modifier = Modifier.padding(
                start = GetPadding().xLargePadding,
                top = GetPadding().xxxLargePadding,
                bottom = GetPadding().largePadding
            ),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Normal
        )

        locationsDetailUiState.locationDetail.type?.let {
            GetInfoInLine(
                ImageVector.vectorResource(id = R.drawable.type),
                stringResource(R.string.type),
                it,
                location = true
            )
        }

        locationsDetailUiState.locationDetail.dimension?.let {
            GetInfoInLine(
                ImageVector.vectorResource(id = R.drawable.dimension),
                stringResource(R.string.dimension),
                it,
                location = true
            )
        }
    }
}