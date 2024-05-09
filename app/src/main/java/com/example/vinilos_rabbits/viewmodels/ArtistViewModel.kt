package com.example.vinilos_rabbits.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinilos_rabbits.services.ArtistSerialized
import com.example.vinilos_rabbits.services.VinilosApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface ArtistUiState {
    data class Success(val artist: List<ArtistSerialized>) : ArtistUiState
    object Error : ArtistUiState
    object Loading : ArtistUiState
}

class ArtistViewModel:  ViewModel() {

    var artistUiState: ArtistUiState by mutableStateOf(ArtistUiState.Loading)
        private set
    var artistIdSelected: Int by mutableStateOf(1)
        private set

    init {        getAllArtists()
    }
    fun getAllArtists(){
        viewModelScope.launch {
            artistUiState = try {
                val response = VinilosApi.retrofitService.getArtists()
                ArtistUiState.Success(response)
            } catch (e: IOException){
                ArtistUiState.Error
            }
        }
    }

    fun setArtistId(artistId: Int){
        artistIdSelected = artistId
    }

}