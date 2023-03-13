package com.example.rickandmorty.ui.screens.commonUtils

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmorty.R

@Composable
fun ScreenNameBar(
    modifier: Modifier = Modifier,
    name: String,
    onFilterClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = onFilterClick
            ) {
                Icon(
                    modifier = Modifier.size(27.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.sort),
                    contentDescription = "Filter"
                )
            }
        }
    }

    Divider(
        modifier = Modifier.height(1.dp),
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
fun GetRowWithFourImages(
    imageUrlLink: List<String>?,
    titleName: String,
    property1: String,
    property2: String,
) {
    var mutableImageLink = imageUrlLink!!.toMutableList()

    if (mutableImageLink.size < 4) {
        for (i in mutableImageLink.size..3) {
            mutableImageLink.add("")
        }
    }

//    for( i in 0..3)
//    {
//        if(i>imageUrlLink.size)
//            mutableImageLink.add("")
//        else
//            mutableImageLink.add(imageUrlLink[i])
//    }

    Card(shape = RoundedCornerShape(10.dp), elevation = 7.dp, modifier = Modifier.padding(5.dp)) {
        Row(
            modifier = Modifier.padding(3.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.weight(1f)) {
                GetImages(mutableImageLink)
            }

            Row(modifier = Modifier.weight(2f)) {
                GetData(
                    titleName,
                    property1,
                    property2
                )
            }
        }
    }
}

@Composable
fun GetImages(imageUrlLink: MutableList<String>) {
    Column() {
        Row() {
            AsyncImage(
                modifier = Modifier
                    .padding(2.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(5.dp)),
                model = imageUrlLink[0],
                error = painterResource(R.drawable.person_image),
                placeholder = painterResource(R.drawable.loading_img),
//                painter = painterResource(id = R.drawable.rick),
                contentDescription = "Icon of Location Characters"
            )

            AsyncImage(
                modifier = Modifier
                    .padding(2.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(5.dp)),
                error = painterResource(R.drawable.person_image),
                placeholder = painterResource(R.drawable.loading_img),
                model = imageUrlLink[1],
                contentDescription = "Icon of Location Characters"
            )
        }

        Row() {
            AsyncImage(
                modifier = Modifier
                    .padding(2.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(5.dp)),
                error = painterResource(R.drawable.person_image),
                placeholder = painterResource(R.drawable.loading_img),
                model = imageUrlLink[2],
                contentDescription = "Icon of Location Characters"
            )

            AsyncImage(
                modifier = Modifier
                    .padding(2.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(5.dp)),
                error = painterResource(R.drawable.person_image),
                placeholder = painterResource(R.drawable.loading_img),
                model = imageUrlLink[3],
                contentDescription = "Icon of Location Characters"
            )
        }
    }
}

@Composable
fun GetData(
    titleName: String,
    property1: String,
    property2: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = titleName,
            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground
        )
        Row() {
            Text(
                text = property1,
                modifier = Modifier.padding(end = 15.dp),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground
            )

            Text(
                text = property2,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}