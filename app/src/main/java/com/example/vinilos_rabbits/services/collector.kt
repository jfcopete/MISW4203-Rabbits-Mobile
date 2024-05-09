package com.example.vinilos_rabbits.services

import kotlinx.serialization.Serializable

@Serializable
data class CollectorSerialized (
    val id: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val comments: List<Comment>,
    val favoritePerformers: List<PerformerSerialized>,
    val collectorAlbums: List<CollectorAlbumSerialized>

)