package com.example.vinilos_rabbits.repositories

import com.example.vinilos_rabbits.models.PrizeDto
import com.example.vinilos_rabbits.services.AddPrizeToArtistResponse
import com.example.vinilos_rabbits.services.CacheVinilosService
import com.example.vinilos_rabbits.services.PrizeSerialized
import com.example.vinilos_rabbits.services.VinilosApi

class PrizeRepository {
    suspend fun getAllPrizes(): List<PrizeSerialized> {
        val cacheVinilosService = CacheVinilosService.getInstance()
        val prizesCached = cacheVinilosService.getPrizes()

        if (!prizesCached.isNullOrEmpty()){
            return prizesCached
        }

        val response = VinilosApi.retrofitService.getPrizes()
        cacheVinilosService.addPrizes(response)

        return response
    }

    suspend fun addPrizeToArtist(prizeId: Int, artistId: Int, prizeDto: PrizeDto): AddPrizeToArtistResponse{
        val response = VinilosApi.retrofitService.addPrizeToArtist(prizeId, artistId, prizeDto)

        return response
    }
}