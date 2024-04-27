package com.example.vinilos_rabbits.activities

import androidx.annotation.StringRes
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
import com.example.vinilos_rabbits.utils.VinilosScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = VinilosScreen.valueOf(
        backStackEntry?.destination?.route ?: VinilosScreen.Start.name
    )

    Scaffold(
        //modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            VinilosTopAppBar(
                currentScreen = currentScreen,
                //scrollBehavior = scrollBehavior,
                modifier = Modifier.background(Color.Blue),
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = VinilosScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = VinilosScreen.Start.name) {
                val homeViewModel: HomeViewModel = viewModel()

                HomeScreen(
                    onAlbumDetails = { navController.navigate(VinilosScreen.AlbumDetail.name) },
                    albumUiState = homeViewModel.homeUiState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )

            }
            composable(route = VinilosScreen.AlbumDetail.name){
                AlbumDetails(
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
    onAlbumDetails: () -> Unit,
    albumUiState: HomeUiState,
    modifier: Modifier = Modifier,
) {
    Column {
        WelcomeText()

        when (albumUiState) {
            is HomeUiState.Loading -> LoadingScreen()
            is HomeUiState.Error -> ErrorScreen()
            is HomeUiState.Success -> AlbumsList(
                onAlbumDetails,
                albumUiState.albums,
                modifier = modifier
            )
        }
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