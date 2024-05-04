package com.example.vinilos_rabbits.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil.compose.rememberAsyncImagePainter

@Composable
fun ArtistCard(
    name: String,
    image: String,
    description: String,
    onArtistDetails: () -> Unit
) {
    Column {
        Surface(onClick=onArtistDetails ){
            Image(
                painter = rememberAsyncImagePainter(image),
                contentDescription = name,
            )
            Text(text = name)
            Text(text = description)
        }
    }
}