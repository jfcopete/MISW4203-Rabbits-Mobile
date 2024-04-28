package com.example.vinilos_rabbits.services

import kotlinx.serialization.Serializable

@Serializable
data class Comment (
    val id: Int,
    val description: String,
    val rating: Int
)