package com.example.vinilos_rabbits.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinilos_rabbits.repositories.AlbumRepository
import com.example.vinilos_rabbits.services.AlbumSerialized
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
    var albumIdSelected: Int by mutableStateOf(1)
        private set

    init {
        getAllAlbums()
    }


    fun getAllAlbums(){
        viewModelScope.launch {
            homeUiState =  try {
                val repository = AlbumRepository()
                val response = repository.getAllAlbums()
                HomeUiState.Success(response)
            } catch (e: IOException){
                HomeUiState.Error
            }
        }
    }

    fun setAlbumId(albumId: Int){
        albumIdSelected = albumId
    }
}