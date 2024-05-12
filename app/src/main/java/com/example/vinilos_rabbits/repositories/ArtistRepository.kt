package com.example.vinilos_rabbits.repositories

import com.example.vinilos_rabbits.services.ArtistSerialized
import com.example.vinilos_rabbits.services.VinilosApi

class ArtistRepository {

    suspend fun getAllArtists(): List<ArtistSerialized> {
        val response = VinilosApi.retrofitService.getArtists()

        return response
    }

    suspend fun getArtistById(artistId: Int): ArtistSerialized {
        val response = VinilosApi.retrofitService.getMusicianById(artistId)

        return response
    }
}