package com.example.vinilos_rabbits.activities

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.vinilos_rabbits.components.ErrorScreen
import com.example.vinilos_rabbits.components.LoadingScreen
import com.example.vinilos_rabbits.services.ArtistSerialized
import com.example.vinilos_rabbits.viewmodels.AlbumUiState
import com.example.vinilos_rabbits.viewmodels.AlbumViewModel
import com.example.vinilos_rabbits.viewmodels.ArtistUiState
import com.example.vinilos_rabbits.viewmodels.ArtistViewModel
import com.example.vinilos_rabbits.viewmodels.HomeUiState

@Composable
fun ArtistDetails(
    artistId: Int,
    modifier: Modifier = Modifier,
){
    val artistViewModel: ArtistViewModel = viewModel()
    val artistUiState = artistViewModel.artistUiState

    // call request
    artistViewModel.getArtistById(artistId=artistId)

    Column(
        modifier = modifier
            .padding(1.dp)
    ) {
        when (artistUiState) {
            is ArtistUiState.Loading -> LoadingScreen()
            is ArtistUiState.Error -> ErrorScreen()
            is ArtistUiState.SuccessDetails -> ArtistDetailsScreen(
                artist= artistUiState.artistDetailed,
            )
            else ->{}
        }
    }
}

@Composable
fun ArtistDetailsScreen(artist: ArtistSerialized) {
    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = rememberAsyncImagePainter(artist.image),
            contentDescription = artist.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = artist.name,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = artist.description,
            style = TextStyle(fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Born: ${artist.birthDate}",
            style = TextStyle(fontSize = 14.sp)
        )
    }
}