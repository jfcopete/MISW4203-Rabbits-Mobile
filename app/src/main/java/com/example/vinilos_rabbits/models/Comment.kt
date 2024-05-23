package com.example.vinilos_rabbits.models

import kotlinx.serialization.Serializable

@Serializable
data class Comment (
    val description: String,
    val rating: String,
    val collector: Collector,
    val album: Album? = null,
    val id: Int? = null
)