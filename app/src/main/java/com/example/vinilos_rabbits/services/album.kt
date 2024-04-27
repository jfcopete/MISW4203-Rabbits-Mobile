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
    val tracks: List<String>,
    val performers: List<String>,
    val comments: List<String>,
    )