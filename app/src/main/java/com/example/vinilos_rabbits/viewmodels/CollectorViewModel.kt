package com.example.vinilos_rabbits.viewmodels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinilos_rabbits.repositories.CollectorRepository
import com.example.vinilos_rabbits.services.CollectorSerialized
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface CollectorUiState {
    data class Success(val collectors: List<CollectorSerialized>) : CollectorUiState
    data class SuccessDetails(val collectorDetailed: CollectorSerialized) : CollectorUiState
    object Error : CollectorUiState
    object Loading : CollectorUiState
}

class CollectorViewModel : ViewModel() {

    private val repository = CollectorRepository()

    var collectorUiState: CollectorUiState by mutableStateOf(CollectorUiState.Loading)
        private set

    var collectorIdSelected: Int by mutableStateOf(0)
        private set

    init {
        getAllCollectors()
    }

    fun getAllCollectors() {
        viewModelScope.launch {
            collectorUiState = try {
                val response = repository.getAllCollectors()
                CollectorUiState.Success(response)
            } catch (e: IOException) {
                CollectorUiState.Error
            }
        }
    }

    fun getCollectorById(collectorId: Int) {
        viewModelScope.launch {
            collectorUiState = try {
                val response = repository.getCollectorById(collectorId)
                CollectorUiState.SuccessDetails(response)
            } catch (e: IOException) {
                CollectorUiState.Error
            }
        }
    }

    fun setCollectorId(collectorId: Int) {
        collectorIdSelected = collectorId
    }
}

