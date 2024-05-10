package com.example.vinilos_rabbits.services

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
//import kotlinx.serialization.Optional


@Serializable
data class PerformerPrizesArtist (
    val id: Int,
    val premiationDate: String? = null,
)