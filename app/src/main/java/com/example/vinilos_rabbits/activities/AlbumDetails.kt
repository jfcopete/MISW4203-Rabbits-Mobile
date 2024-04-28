package com.example.vinilos_rabbits.activities

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.vinilos_rabbits.R
import com.example.vinilos_rabbits.components.ErrorScreen
import com.example.vinilos_rabbits.components.LoadingScreen
import com.example.vinilos_rabbits.services.AlbumSerialized
import com.example.vinilos_rabbits.services.Comment
import com.example.vinilos_rabbits.services.TrackSerialized
import com.example.vinilos_rabbits.viewmodels.AlbumUiState
import com.example.vinilos_rabbits.viewmodels.AlbumViewModel
import com.example.vinilos_rabbits.viewmodels.HomeUiState

@Composable
fun AlbumDetails(
    albumId: Int,
    modifier: Modifier = Modifier,
){
    val albumViewModel: AlbumViewModel = viewModel()
    val albumUiState = albumViewModel.albumUiState

    // call request
    albumViewModel.getAlbumById(albumId=albumId)

    Column(
        modifier = modifier
            .padding(1.dp)
    ) {
        when (albumUiState) {
            is AlbumUiState.Loading -> LoadingScreen()
            is AlbumUiState.Error -> ErrorScreen()
            is AlbumUiState.Success -> AlbumDetailsScreen(
                album= albumUiState.album,
            )
        }
    }
}


@Composable
fun AlbumDetailsScreen(
    album: AlbumSerialized
) {
    Column() {
        Image(
            painter = rememberAsyncImagePainter(album.cover),
            contentDescription = album.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = album.name)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = album.description)
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = stringResource(R.string.songs), style = TextStyle(
            fontSize = 24.sp
        ))
        Tracks(tracks = album.tracks)

        Text(text = stringResource(R.string.comments), style = TextStyle(
            fontSize = 24.sp
        ))
        Comments(comments = album.comments)
    }
}

@Composable
fun Tracks(tracks: List<TrackSerialized>?){
    Column {
        tracks?.forEach {
            Row {
                Text(text = it.name)
                Text(text = " - ")
                Text(text = it.duration)
            }
    }
    }
}

@Composable
fun Comments(comments: List<Comment>?){
    Column {
        comments?.forEach {
            Row (
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Text(text = it.description)
            }
        }
    }
}