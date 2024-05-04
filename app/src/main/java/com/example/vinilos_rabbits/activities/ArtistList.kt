package com.example.vinilos_rabbits.activities

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.vinilos_rabbits.components.AlbumCard
import com.example.vinilos_rabbits.services.AlbumSerialized

@Composable
fun ArtistListScreen(
    modifier: Modifier
){
    Column(
        modifier = modifier
    ) {
        Text(text = "Artist")
    }
}




@Composable
fun ArtistList(
    onAlbumDetails: () -> Unit,
    albums: List<AlbumSerialized>,
    modifier: Modifier
) {
    Column {
        albums.forEach { album ->
            AlbumCard(
                img = album.cover,
                name = album.name,
                description = album.genre,
                onAlbumDetails = onAlbumDetails
            )
        }
    }
}