package com.example.vinilos_rabbits.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.vinilos_rabbits.R
import com.example.vinilos_rabbits.utils.VinilosScreen

@Composable
fun VinilosBottomBar(
    currentCategory: VinilosScreen,
    navController: NavHostController,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier.height(54.dp),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer // Ajusta el color de los íconos
    ) {

        IconButton(
            onClick = { navController.navigate(VinilosScreen.Start.name) },
            modifier = Modifier.weight(1f).height(50.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_album),
                contentDescription = stringResource(R.string.aria_album_list),
                modifier = Modifier.height(50.dp).width(50.dp)
            )
        }

        IconButton(
            onClick = { navController.navigate(VinilosScreen.Artist.name) },
            modifier = Modifier.weight(1f).height(50.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_artist),
                contentDescription = stringResource(R.string.aria_artist_list),
                modifier = Modifier.height(50.dp).width(50.dp)
            )
        }

        IconButton(
            onClick = { navController.navigate(VinilosScreen.Collector.name) },
            modifier = Modifier.weight(1f).height(50.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_collector),
                contentDescription = stringResource(R.string.aria_collector_list),
                modifier = Modifier.height(50.dp).width(50.dp)
            )
        }
    }
}
