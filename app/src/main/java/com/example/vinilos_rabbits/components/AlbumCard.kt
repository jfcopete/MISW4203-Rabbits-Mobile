package com.example.vinilos_rabbits.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

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
                    contentDescription = name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
                Column(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = name,)
                    Text(text = description, )
                }
            }
        }

}