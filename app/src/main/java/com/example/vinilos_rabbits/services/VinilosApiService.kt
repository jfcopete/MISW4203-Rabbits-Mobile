package com.example.vinilos_rabbits.services

import com.example.vinilos_rabbits.models.Album
import com.example.vinilos_rabbits.models.Artist
import com.example.vinilos_rabbits.models.Comment
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_URL = "http://34.228.22.154:3000/"

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

    // Agregar un comentario a un álbum
    @POST("albums/{albumId}/comments")
    suspend fun addComment(@Path("albumId") albumId: Int, @Body comment: Comment): Comment

    @GET("collectors/{collectorId}")
    suspend fun getCollectorById(@Path("collectorId") collectorId: Int): CollectorSerialized
}

object VinilosApi {
    val retrofitService : VinilosApiService by lazy {
        retrofit.create(VinilosApiService::class.java)
    }
}