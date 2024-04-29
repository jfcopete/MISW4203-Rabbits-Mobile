package com.example.vinilos_rabbits.services

import kotlinx.serialization.Serializable

@Serializable
data class TrackSerialized (
    val id: Int,
    val name: String,
    val duration: String
)