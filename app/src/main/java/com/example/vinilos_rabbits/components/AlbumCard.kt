package com.example.vinilos_rabbits.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil.compose.rememberAsyncImagePainter

@Composable
fun AlbumCard(
    img: String,
    name: String,
    description: String,
    onAlbumDetails: () -> Unit
) {
    Column {
        Surface(onClick=onAlbumDetails ){
            Image(
                painter = rememberAsyncImagePainter(img),
                contentDescription = name,
            )
            Text(text = name)
            Text(text = description)
        }
    }
}