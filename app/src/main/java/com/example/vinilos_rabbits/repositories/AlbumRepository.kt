package com.example.vinilos_rabbits.repositories

import com.example.vinilos_rabbits.services.AlbumSerialized
import com.example.vinilos_rabbits.services.VinilosApi

class AlbumRepository {
    suspend fun getAlbumById(albumId: Int): AlbumSerialized{
        val response = VinilosApi.retrofitService.getAlbum(albumId)

        return response
    }

    suspend fun getAllAlbums(): List<AlbumSerialized>{
        val response = VinilosApi.retrofitService.getAlbums()

        return response
    }
}