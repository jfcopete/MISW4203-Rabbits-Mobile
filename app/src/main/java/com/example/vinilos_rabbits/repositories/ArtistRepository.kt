package com.example.vinilos_rabbits.repositories

import com.example.vinilos_rabbits.services.ArtistSerialized
import com.example.vinilos_rabbits.services.VinilosApi

class ArtistRepository {
    suspend fun getAllArtists(): List<ArtistSerialized>{
        return VinilosApi.retrofitService.getArtists()
    }

    suspend fun getArtistById(artistId: Int): ArtistSerialized {
        return VinilosApi.retrofitService.getMusicianById(artistId)
    }
}