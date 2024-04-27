package com.example.vinilos_rabbits.services

import com.example.vinilos_rabbits.models.Album
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.http.GET

private const val BASE_URL = "http://34.28.129.54:3000/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface VinilosApiService {
     @GET("albums")
     suspend fun getAlbums(): List<AlbumSerialized>
}

object VinilosApi {
    val retrofitService : VinilosApiService by lazy {
        retrofit.create(VinilosApiService::class.java)
    }
}