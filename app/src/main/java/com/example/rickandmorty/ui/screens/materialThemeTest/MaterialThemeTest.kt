package com.example.rickandmorty.ui.screens.materialThemeTest

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.ui.theme.RickAndMortyTheme

@Composable
@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_NO)
fun MaterialThemeTesting()
{
    RickAndMortyTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(text = "Heading 1", style = MaterialTheme.typography.h1)
                Text(text = "Heading 2", style = MaterialTheme.typography.h2)
                Text(text = "Body 1", style = MaterialTheme.typography.body1)
                Text(text = "Body 2", style = MaterialTheme.typography.body2)
            
                Box(modifier=Modifier.size(100.dp).background(MaterialTheme.colors.primary)) {
                    
                }
            }
        }
    }
}
