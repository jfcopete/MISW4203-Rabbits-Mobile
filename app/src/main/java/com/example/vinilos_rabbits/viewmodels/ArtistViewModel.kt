package com.example.vinilos_rabbits.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinilos_rabbits.repositories.ArtistRepository
import com.example.vinilos_rabbits.services.ArtistSerialized
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface ArtistUiState {
    data class Success(val artist: List<ArtistSerialized>) : ArtistUiState
    data class SuccessDetails(val artistDetailed: ArtistSerialized) : ArtistUiState
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
                val repository = ArtistRepository()
                val response =  repository.getAllArtists()
                ArtistUiState.Success(response)
            } catch (e: IOException){
                ArtistUiState.Error
            }
        }
    }

    fun setArtistId(artistId: Int){
        artistIdSelected = artistId
    }

    fun getArtistById(artistId: Int){
        viewModelScope.launch {
            artistUiState = try {
                val repository = ArtistRepository()
                val response = repository.getArtistById(artistId)
                ArtistUiState.SuccessDetails(response)
            } catch (e: IOException){
                ArtistUiState.Error
            }
        }
    }

}