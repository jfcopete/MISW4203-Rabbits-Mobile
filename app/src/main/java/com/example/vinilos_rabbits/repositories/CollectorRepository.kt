package com.example.vinilos_rabbits.repositories

import com.example.vinilos_rabbits.services.CollectorSerialized
import com.example.vinilos_rabbits.services.VinilosApi

class CollectorRepository {
    suspend fun getAllCollectors(): List<CollectorSerialized> {
        val response = VinilosApi.retrofitService.getCollectors()

        return response
    }
}