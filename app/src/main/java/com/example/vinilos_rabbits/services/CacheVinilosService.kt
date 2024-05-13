package com.example.vinilos_rabbits.services

import android.content.Context

class CacheVinilosService() {
    companion object {
        private var instance: CacheVinilosService? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: CacheVinilosService().also {
                instance = it
            }
        }
    }

        private var collectors: List<CollectorSerialized>? = null

        fun addCollectors(newCollectors: List<CollectorSerialized>){
            if(newCollectors.isNotEmpty()) {
                collectors = newCollectors
            }
        }

        fun getCollectors(): List<CollectorSerialized>?{
            return collectors
        }

}