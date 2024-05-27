package com.example.vinilos_rabbits.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.vinilos_rabbits.R

@Composable
fun AlbumCard(
    img: String,
    name: String,
    description: String,
    onAlbumDetails: () -> Unit
) {
        Surface(
            onClick=onAlbumDetails,
        ){
            Column(
            ) {
                Image(
                    painter = rememberAsyncImagePainter(img),
                    contentDescription = stringResource(id = R.string.album),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
                Column(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = name,)
                    Text(text = "${stringResource(R.string.genere)}: ${description}")
                }
            }
        }

}