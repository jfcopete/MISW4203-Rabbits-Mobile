package com.example.vinilos_rabbits.repositories
import com.example.vinilos_rabbits.services.CacheVinilosService
import com.example.vinilos_rabbits.services.CollectorSerialized
import com.example.vinilos_rabbits.services.VinilosApi
import android.app.Application

class CollectorRepository() {
    suspend fun getAllCollectors(): List<CollectorSerialized> {
        val cacheVinilosService = CacheVinilosService.getInstance()
        val collectorsCached = cacheVinilosService.getCollectors()

        if(collectorsCached != null && collectorsCached.isNotEmpty()){
            return collectorsCached
        }

        val response = VinilosApi.retrofitService.getCollectors()
        cacheVinilosService.addCollectors(response)


        return response
    }

    suspend fun getCollectorById(collectorId: Int): CollectorSerialized {
        return VinilosApi.retrofitService.getCollectorById(collectorId)
    }
}