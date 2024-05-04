package com.example.vinilos_rabbits.services

import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    val id: Int,
    val name: String,
    val image: String,
    val description: String
)