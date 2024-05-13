package com.example.vinilos_rabbits.repositories

import com.example.vinilos_rabbits.services.AlbumSerialized
import com.example.vinilos_rabbits.services.VinilosApi

class AlbumRepository {
    suspend fun getAlbumById(albumId: Int): AlbumSerialized {
        return VinilosApi.retrofitService.getAlbum(albumId)
    }

    suspend fun getAllAlbums(): List<AlbumSerialized>{
        return VinilosApi.retrofitService.getAlbums()
    }
}