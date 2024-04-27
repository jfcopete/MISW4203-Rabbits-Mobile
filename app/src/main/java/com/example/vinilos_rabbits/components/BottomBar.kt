package com.example.vinilos_rabbits.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.vinilos_rabbits.R
import com.example.vinilos_rabbits.utils.VinilosCategory
import com.example.vinilos_rabbits.utils.VinilosScreen
import com.example.vinilos_rabbits.components.SelectCategoryButton

@Composable
fun VinilosBottomBar(
    currentCategory: VinilosScreen,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Row {
        SelectCategoryButton(
            labelResourceId = R.string.album,
            onClick = { navController.navigate(VinilosScreen.Start.name) },
            modifier = modifier
        )
        SelectCategoryButton(
            labelResourceId = R.string.artist,
            onClick = { navController.navigate(VinilosScreen.Artist.name) },
            modifier = modifier
        )
        SelectCategoryButton(
            labelResourceId = R.string.collector,
            onClick = { navController.navigate(VinilosScreen.Collector.name) },
            modifier = modifier
        )

    }
}