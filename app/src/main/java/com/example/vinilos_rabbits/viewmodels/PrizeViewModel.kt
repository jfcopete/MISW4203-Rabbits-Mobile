package com.example.vinilos_rabbits.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.vinilos_rabbits.services.PrizeSerialized
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import com.example.vinilos_rabbits.repositories.PrizeRepository
import java.io.IOException
import androidx.lifecycle.viewModelScope
import com.example.vinilos_rabbits.models.PrizeDto
import com.example.vinilos_rabbits.services.AddPrizeToArtistResponse
import java.text.SimpleDateFormat
import java.util.Date

sealed interface PrizeUiState {
    data class Success(val prizes: List<PrizeSerialized>): PrizeUiState
    object Error : PrizeUiState
    object Loading : PrizeUiState
}

sealed interface PrizeToArtistUiState {
    data class Success(val prizes: AddPrizeToArtistResponse?): PrizeToArtistUiState
    object Error : PrizeToArtistUiState
    object Loading : PrizeToArtistUiState

    object Off : PrizeToArtistUiState
}

class PrizeViewModel: ViewModel() {
    var prizesUiState: PrizeUiState by mutableStateOf(PrizeUiState.Loading)
        private set

    var prizeToArtistUiState: PrizeToArtistUiState by mutableStateOf(PrizeToArtistUiState.Off)
        private set

    fun getPrizes() {
        viewModelScope.launch(){
            prizesUiState = try {
                val repository = PrizeRepository()
                val response = repository.getAllPrizes()
                PrizeUiState.Success(response)
            } catch (e: IOException){
                PrizeUiState.Error
            }
        }
    }

    fun addPrizeToArtist(prizeId: Int, artistId: Int, premiationDate: Date) {
        viewModelScope.launch() {
            prizeToArtistUiState = try {
                PrizeToArtistUiState.Loading
                val repository = PrizeRepository()
                val formattedDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(premiationDate)
                val prizeDto = PrizeDto(formattedDate)
                val response = repository.addPrizeToArtist(prizeId = prizeId, artistId= artistId, prizeDto)
                PrizeToArtistUiState.Success(response)
            } catch (e: IOException){
                PrizeToArtistUiState.Error
            }

        }
    }

}