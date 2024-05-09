package com.example.vinilos_rabbits.services

import kotlinx.serialization.Serializable

@Serializable
data class AlbumSerialized(
    val id: Int,
    val name: String,
    val cover: String,
    val description: String,
    val releaseDate: String,
    val genre: String,
    val recordLabel: String,
    val tracks: List<TrackSerialized>?,
    val performers: List<PerformerSerialized>?,
    val comments: List<Comment>?
    )

@Serializable
data class CollectorAlbumSerialized(
    val id: Int,
    val status: String?,
    val price: Int?,
)