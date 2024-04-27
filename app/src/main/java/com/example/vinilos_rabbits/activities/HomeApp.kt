package com.example.vinilos_rabbits.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vinilos_rabbits.R
import com.example.vinilos_rabbits.viewmodels.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinilos_rabbits.components.AlbumCard
import com.example.vinilos_rabbits.components.ErrorScreen
import com.example.vinilos_rabbits.components.LoadingScreen
import com.example.vinilos_rabbits.components.VinilosTopAppBar
import com.example.vinilos_rabbits.services.AlbumSerialized
import com.example.vinilos_rabbits.viewmodels.HomeUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { VinilosTopAppBar(scrollBehavior = scrollBehavior, modifier = Modifier.background(
            Color.Blue
        )) }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val homeViewModel: HomeViewModel = viewModel()
            WelcomeText()
            HomeScreen(
                albumUiState = homeViewModel.homeUiState,
                contentPadding = it
                )
        }
    }
}

@Composable
fun HomeScreen(
    albumUiState: HomeUiState,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,
) {
    when (albumUiState) {
        is HomeUiState.Loading -> LoadingScreen()
        is HomeUiState.Error -> ErrorScreen()
        is HomeUiState.Success -> AlbumsList(
            albumUiState.albums,
            modifier.padding(top = contentPadding.calculateTopPadding())
        )
    }

}

@Composable
fun WelcomeText() {
    Text(
        text= stringResource(R.string.welcome_title),
        fontSize = 40.sp,
        textAlign = TextAlign.Center,
    )
}


@Composable
fun AlbumsList(
    albums: List<AlbumSerialized>,
    modifier: Modifier
) {
    Row {
        albums.forEach { album ->
            AlbumCard(img = album.cover, name = album.name, description = album.description )
        }
    }
}