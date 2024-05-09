package com.example.vinilos_rabbits.viewmodels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinilos_rabbits.services.AlbumSerialized
import com.example.vinilos_rabbits.services.CollectorSerialized
import com.example.vinilos_rabbits.services.VinilosApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface CollectorUiState {
    data class Success(val collectors: List<CollectorSerialized>) : CollectorUiState
    object Error : CollectorUiState
    object Loading : CollectorUiState
}

class CollectorViewModel: ViewModel(){

    var collectorUiState: CollectorUiState by mutableStateOf(CollectorUiState.Loading)
        private set

    init {
        getAllCollectors()
    }

    fun getAllCollectors(){
        viewModelScope.launch {
            collectorUiState = try {
                val response = VinilosApi.retrofitService.getCollectors()
                CollectorUiState.Success(response)
            } catch (e: IOException) {
                CollectorUiState.Error
            }
        }
    }

}

// analisis de capacidad