package com.example.vinilos_rabbits.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
        modifier = modifier.height(50.dp),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer // Ajusta el color de los íconos
    ) {

        IconButton(
            onClick = { navController.navigate(VinilosScreen.Start.name) },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_album),
                contentDescription = "Álbum",
                modifier = Modifier.size(40.dp)
            )
        }

        IconButton(
            onClick = { navController.navigate(VinilosScreen.Artist.name) },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_artist),
                contentDescription = "Artista",
                modifier = Modifier.size(40.dp)
            )
        }

        IconButton(
            onClick = { navController.navigate(VinilosScreen.Collector.name) },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_collector),
                contentDescription = "Coleccionista",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}
