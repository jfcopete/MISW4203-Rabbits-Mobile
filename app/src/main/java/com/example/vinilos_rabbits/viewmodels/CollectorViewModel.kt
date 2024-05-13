package com.example.vinilos_rabbits.viewmodels
import android.app.Application
import android.util.Log
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
    object Error : CollectorUiState
    object Loading : CollectorUiState
}

class CollectorViewModel(): ViewModel(){

    private val repository = CollectorRepository()

    var collectorUiState: CollectorUiState by mutableStateOf(CollectorUiState.Loading)
        private set

    init {
        getAllCollectors()
    }

    fun getAllCollectors(){
        viewModelScope.launch {
            collectorUiState = try {
                Log.i("getCollertors", "**")
                val response = repository.getAllCollectors()
                Log.i("getCollertors res", response.toString())
                CollectorUiState.Success(response)
            } catch (e: IOException) {
                Log.i("getCollertors Error", e.toString())
                CollectorUiState.Error
            }
        }
    }

}

