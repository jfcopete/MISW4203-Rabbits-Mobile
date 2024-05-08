package com.example.vinilos_rabbits.services

import kotlinx.serialization.Serializable

@Serializable
data class ArtistSerialized(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
    val albums: List<AlbumSerialized>?,
    val performerPrizes: List<PerformerSerialized>?,
)

