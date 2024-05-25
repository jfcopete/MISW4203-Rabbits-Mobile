package com.example.vinilos_rabbits.repositories

import com.example.vinilos_rabbits.services.AlbumSerialized
import com.example.vinilos_rabbits.services.VinilosApi
import com.example.vinilos_rabbits.models.Comment

class AlbumRepository {
    suspend fun getAlbumById(albumId: Int): AlbumSerialized {
        return VinilosApi.retrofitService.getAlbum(albumId)
    }

    suspend fun getAllAlbums(): List<AlbumSerialized>{
        return VinilosApi.retrofitService.getAlbums()
    }

    suspend fun addCommentToAlbum(albumId: Int, comment: Comment): Comment {
        return VinilosApi.retrofitService.addComment(albumId, comment)
    }
}