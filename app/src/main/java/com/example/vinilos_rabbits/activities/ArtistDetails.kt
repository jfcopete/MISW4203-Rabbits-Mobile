package com.example.vinilos_rabbits.activities

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.vinilos_rabbits.R
import com.example.vinilos_rabbits.components.ErrorScreen
import com.example.vinilos_rabbits.components.LoadingScreen
import com.example.vinilos_rabbits.services.ArtistSerialized
import com.example.vinilos_rabbits.utils.VinilosScreen
import com.example.vinilos_rabbits.viewmodels.AlbumUiState
import com.example.vinilos_rabbits.viewmodels.AlbumViewModel
import com.example.vinilos_rabbits.viewmodels.ArtistUiState
import com.example.vinilos_rabbits.viewmodels.ArtistViewModel
import com.example.vinilos_rabbits.viewmodels.HomeUiState

@Composable
fun ArtistDetails(
    artistId: Int,
    modifier: Modifier = Modifier,
    navigation: NavHostController
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
                navigation = navigation
            )
            else ->{}
        }
    }
}

@Composable
fun ArtistDetailsScreen(
    artist: ArtistSerialized,
    navigation:  NavHostController
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            contentDescription = "portada del album",
            painter = rememberAsyncImagePainter(artist.image),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row (
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = artist.name,
                style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            )
            TextButton(
                modifier = Modifier.weight(1f)
                    .padding(0.dp),
                onClick = { navigation.navigate(VinilosScreen.AddReward.name) },
            ){
                Icon(Icons.Filled.Add, stringResource(R.string.add_reward_to_artist))
                Text(text = stringResource(R.string.artist_reward))
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = artist.description,
            style = TextStyle(fontSize = 20.sp),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Born: ${artist.birthDate}",
            style = TextStyle(fontSize = 20.sp),
            color = Color.White
        )
    }
}