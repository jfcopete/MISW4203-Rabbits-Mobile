package com.example.vinilos_rabbits.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinilos_rabbits.services.AlbumSerialized
import com.example.vinilos_rabbits.services.VinilosApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AlbumUiState {
    data class Success(val album: AlbumSerialized) : AlbumUiState
    object Error : AlbumUiState
    object Loading : AlbumUiState
}

class AlbumViewModel:  ViewModel() {

   var albumUiState: AlbumUiState by mutableStateOf(AlbumUiState.Loading)
        private set

    fun getAlbumById(albumId: Int){
        viewModelScope.launch {
            albumUiState = try {
                val response = VinilosApi.retrofitService.getAlbum(albumId)
                AlbumUiState.Success(response)
            } catch (e: IOException){
                AlbumUiState.Error
            }
        }
    }

    init{}


}