package com.example.vinilos_rabbits.activities

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vinilos_rabbits.services.ArtistSerialized
import androidx.compose.foundation.lazy.items
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinilos_rabbits.components.ArtistCard
import com.example.vinilos_rabbits.components.ErrorScreen
import com.example.vinilos_rabbits.components.LoadingScreen
import com.example.vinilos_rabbits.viewmodels.ArtistUiState
import com.example.vinilos_rabbits.viewmodels.ArtistViewModel
import com.example.vinilos_rabbits.viewmodels.HomeUiState

@Composable
fun ArtistListScreen(
//    onArtistDetails: (artistId: Int) -> Unit,
//    artistUiState: ArtistUiState,
    modifier: Modifier
){
    val artistViewModel: ArtistViewModel = viewModel()
    val artistUiState = artistViewModel.artistUiState

    Column(
        modifier = modifier
            .padding(1.dp),
    ) {
        when (artistUiState) {
            is ArtistUiState.Loading -> LoadingScreen()
            is ArtistUiState.Error -> ErrorScreen()
            is ArtistUiState.Success -> ArtistList(artistUiState.artist)
        }
    }
}


@Composable
fun ArtistList(
//    onArtistDetails: (artistId: Int) -> Unit,
    artists: List<ArtistSerialized>,
) {
    LazyColumn (
    ) {
        items(artists){artist ->
            Box(
                modifier = Modifier.padding(bottom = 15.dp)
            ){
                ArtistCard(
                    name = artist.name,
                    image =  artist.image,
                    description =  artist.description
//                    onArtistDetails = { onArtistDetails(artist.id) }
                )
            }
        }
    }
}