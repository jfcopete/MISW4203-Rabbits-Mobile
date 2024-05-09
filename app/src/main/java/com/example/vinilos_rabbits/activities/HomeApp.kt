package com.example.vinilos_rabbits.activities

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vinilos_rabbits.components.VinilosBottomBar
import com.example.vinilos_rabbits.utils.VinilosScreen
import androidx.compose.foundation.lazy.items


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = VinilosScreen.valueOf(
        backStackEntry?.destination?.route ?: VinilosScreen.Start.name
    )
    val homeViewModel: HomeViewModel = viewModel()

    Scaffold(
        //modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            VinilosTopAppBar(
                currentScreen = currentScreen,
                //scrollBehavior = scrollBehavior,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        bottomBar = {
            VinilosBottomBar(
                currentCategory = currentScreen,
                navController = navController,
                canNavigateBack = false
//                canNavigateBack = navController.previousBackStackEntry != null,
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = VinilosScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = VinilosScreen.Start.name) {


                HomeScreen(
                    onAlbumDetails = {albumId ->
                        homeViewModel.setAlbumId(albumId)
                        navController.navigate(VinilosScreen.AlbumDetail.name)
                                     },
                    albumUiState = homeViewModel.homeUiState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )

            }
            composable(route = VinilosScreen.AlbumDetail.name){
                AlbumDetails(
                    albumId = homeViewModel.albumIdSelected,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            composable(route = VinilosScreen.Artist.name){
                ArtistListScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            composable(route = VinilosScreen.Collector.name){
                CollectorList(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }


        }


    }
}

@Composable
fun HomeScreen(
    onAlbumDetails: (albumId: Int) -> Unit,
    albumUiState: HomeUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(1.dp),
    ) {
            when (albumUiState) {
                is HomeUiState.Loading -> LoadingScreen()
                is HomeUiState.Error -> ErrorScreen()
                is HomeUiState.Success -> AlbumsList(
                    onAlbumDetails,
                    albumUiState.albums,
                )
            }

    }

}

@Composable
fun WelcomeText() {
    Text(
        text= stringResource(R.string.welcome_title),
        fontSize = 38.sp,
        textAlign = TextAlign.Center,
    )
}


@Composable
fun AlbumsList(
    onAlbumDetails: (albumId: Int) -> Unit,
    albums: List<AlbumSerialized>,
) {
    LazyColumn (
    ) {
        items(albums) {album ->
            Box(
                modifier = Modifier.padding(bottom = 15.dp)
            ) {
                AlbumCard(
                    img = album.cover,
                    name = album.name,
                    description = album.genre,
                    onAlbumDetails = { onAlbumDetails(album.id) }
                )
            }
        }
    }
}