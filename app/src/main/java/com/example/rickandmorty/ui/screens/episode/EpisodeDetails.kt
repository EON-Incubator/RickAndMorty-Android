package com.example.rickandmorty.ui.screens.episode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.NavigationDestination

@Composable
fun EpisodeDetails() {
    val viewModel = hiltViewModel<EpisodeViewModel>()
    val state by viewModel.state.collectAsState()
}

object EpisodeDetailsDestination : NavigationDestination {
    override val route = "episode_detail"
    override val screenTitleRes = R.string.episode_detail_screen_title
}

@Composable
fun EpisodeDetailScreen(
    state: EpisodeViewModel.EpisodesState,
) {
//    Card(
//        shape = RoundedCornerShape(10.dp),
//        elevation = 7.dp,
//        modifier = Modifier.padding(5.dp)
//            .clickable {
//                onClickable(id)
//            }
//
//    ) {
//        Row(
//            modifier = Modifier.padding(3.dp),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Row(modifier = Modifier.weight(1f)) {
//                AsyncImage(
//                    modifier = Modifier
//                        .padding(2.dp)
//                        .weight(1f)
//                        .clip(RoundedCornerShape(5.dp)),
//                    model = imageUrlLink[0],
//                    error = painterResource(R.drawable.person_image),
//                    placeholder = painterResource(R.drawable.loading_img),
// //                painter = painterResource(id = R.drawable.rick),
//                    contentDescription = "Icon of Location Characters"
//                )
//            }
//
//            Row(modifier = Modifier.weight(2f)) {
//                GetData(
//                    titleName,
//                    property1,
//                    property2
//                )
//            }
//        }
//    }
}