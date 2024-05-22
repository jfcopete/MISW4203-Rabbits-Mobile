package com.example.vinilos_rabbits.services

import kotlinx.serialization.Serializable

@Serializable
data class PrizeSerialized (
    val id: Int,
    val organization: String,
    val name: String,
    val description: String,
    val performerPrizes: List<PerformerPrizeSerialized>

)

@Serializable
data class PerformerPrizeSerialized (
    val id: Int?,
    val premiationDate: String?
)