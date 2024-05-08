package com.example.vinilos_rabbits.services

import com.example.vinilos_rabbits.models.Album
import com.example.vinilos_rabbits.models.Artist
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "http://34.41.37.149:3000/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface VinilosApiService {
     @GET("albums")
     suspend fun getAlbums(): List<AlbumSerialized>

    @GET("albums/{albumId}")
    suspend fun getAlbum(
        @Path("albumId") albumId: Int
    ): AlbumSerialized

    @GET("artists")
    suspend fun getArtists(): List<ArtistSerialized>
}

object VinilosApi {
    val retrofitService : VinilosApiService by lazy {
        retrofit.create(VinilosApiService::class.java)
    }
}