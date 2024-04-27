package com.example.vinilos_rabbits.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinilos_rabbits.models.Album
import com.example.vinilos_rabbits.services.AlbumSerialized
import com.example.vinilos_rabbits.services.VinilosApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface HomeUiState {
    data class Success(val albums: List<AlbumSerialized>) : HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState
}

class HomeViewModel: ViewModel() {

    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getAllAlbums()
    }


    fun getAllAlbums(){
        viewModelScope.launch {
            homeUiState =  try {
                val response = VinilosApi.retrofitService.getAlbums()
                HomeUiState.Success(response)
            } catch (e: IOException){
                Log.e("***********+", e.toString())
                HomeUiState.Error
            }
        }
    }
}