package com.example.vinilos_rabbits.activities

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinilos_rabbits.components.AlbumCard
import com.example.vinilos_rabbits.components.ErrorScreen
import com.example.vinilos_rabbits.components.LoadingScreen
import com.example.vinilos_rabbits.services.CollectorSerialized
import com.example.vinilos_rabbits.viewmodels.AlbumUiState
import com.example.vinilos_rabbits.viewmodels.CollectorUiState
import com.example.vinilos_rabbits.viewmodels.CollectorViewModel

@Composable
fun CollectorList(
    modifier: Modifier
){
    val collectionViewModel: CollectorViewModel = viewModel()
    val collectorUiState = collectionViewModel.collectorUiState

    Column(
        modifier = modifier
            .padding(1.dp)
    ) {
        when (collectorUiState) {
            is CollectorUiState.Loading -> LoadingScreen()
            is CollectorUiState.Error -> ErrorScreen()
            is CollectorUiState.Success -> CollectorListScreen(
                collectors=collectorUiState.collectors,
            )
        }
    }

}

@Composable
fun CollectorListScreen(
    collectors: List<CollectorSerialized>
){
    LazyColumn (
    ) {
        items(collectors) {collector ->
            Box(
                modifier = Modifier.padding(bottom = 15.dp)
            ) {
                Column {
                    Text(text = collector.name)
                    Text(text = collector.email)
                }
            }
        }
    }
}
