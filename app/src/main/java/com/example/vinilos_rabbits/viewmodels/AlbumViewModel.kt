package com.example.vinilos_rabbits.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinilos_rabbits.models.Comment
import com.example.vinilos_rabbits.repositories.AlbumRepository
import com.example.vinilos_rabbits.services.AlbumSerialized
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AlbumUiState {
    data class Success(val album: AlbumSerialized) : AlbumUiState
    object Error : AlbumUiState
    object Loading : AlbumUiState
}

class AlbumViewModel(private val repository: AlbumRepository = AlbumRepository()) : ViewModel() {

    private val _albumUiState = MutableStateFlow<AlbumUiState>(AlbumUiState.Loading)
    val albumUiState: StateFlow<AlbumUiState> = _albumUiState

    private val _newCommentUiState = MutableStateFlow<Comment?>(null)
    val newCommentUiState: StateFlow<Comment?> = _newCommentUiState

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getAlbumById(albumId: Int) {
        viewModelScope.launch {
            _albumUiState.value = try {
                val response = repository.getAlbumById(albumId)
                AlbumUiState.Success(response)
            } catch (e: IOException) {
                AlbumUiState.Error
            }
        }
    }

    fun addCommentToAlbum(albumId: Int, comment: Comment) {
        viewModelScope.launch {
            try {
                println("Adding comment to albumId=$albumId: $comment") // Agregar esta línea
                val addedComment = repository.addCommentToAlbum(albumId, comment)
                _newCommentUiState.value = addedComment
                // Actualizar la información del álbum después de agregar un nuevo comentario
                getAlbumById(albumId)
            } catch (e: IOException) {
                _error.value = e.message
            }
        }
    }
}

