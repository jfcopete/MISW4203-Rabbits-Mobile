package com.example.vinilos_rabbits.activities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinilos_rabbits.components.ErrorScreen
import com.example.vinilos_rabbits.components.LoadingScreen
import com.example.vinilos_rabbits.services.CollectorSerialized
import com.example.vinilos_rabbits.viewmodels.CollectorUiState
import com.example.vinilos_rabbits.viewmodels.CollectorViewModel

@Composable
fun CollectorDetails(
    collectorId: Int,
    modifier: Modifier = Modifier,
){
    val collectorViewModel: CollectorViewModel = viewModel()
    val collectorUiState = collectorViewModel.collectorUiState

    // call request
    collectorViewModel.getCollectorById(collectorId=collectorId)

    Column(
        modifier = modifier
            .padding(1.dp)
    ) {
        when (collectorUiState) {
            is CollectorUiState.Loading -> LoadingScreen()
            is CollectorUiState.Error -> ErrorScreen()
            is CollectorUiState.SuccessDetails -> CollectorDetailsScreen(
                collector = collectorUiState.collectorDetailed,
            )
            else ->{}
        }
    }
}

@Composable
fun CollectorDetailsScreen(collector: CollectorSerialized) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            Text(text = "Nombre: ${collector.name}",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Email: ${collector.email}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Telefono: ${collector.telephone}")
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Artistas Favoritos",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
            collector.favoritePerformers.forEach { performer ->
                Text(text = performer.name)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "ID de Álbumes favoritos",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
            collector.collectorAlbums.forEach { album ->
                Text(text = album.id.toString())
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Comentarios",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
            collector.comments.forEach { comment ->
                Text(text = comment.description)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
