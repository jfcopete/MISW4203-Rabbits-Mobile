package com.example.vinilos_rabbits.services

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://0e77-181-58-38-212.ngrok-free.app/"

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

    @GET("musicians")
    suspend fun getArtists(): List<ArtistSerialized>

    @GET("collectors")
    suspend fun getCollectors(): List<CollectorSerialized>

    @GET("musicians/{artistId}")
    suspend fun getMusicianById(@Path("artistId") artistId:Int):ArtistSerialized

    @GET("prizes")
    suspend fun getPrizes(): List<PrizeSerialized>
}

object VinilosApi {
    val retrofitService : VinilosApiService by lazy {
        retrofit.create(VinilosApiService::class.java)
    }
}