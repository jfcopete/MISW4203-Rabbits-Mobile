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



@Serializable
data class AddPrizeToArtistResponse(
    val id: Int,
    val performer: Performer,
    val prize: Prize,
    val premiationDate: String,
)

@Serializable
data class Prize(
    val id: Int,
    val organization: String,
    val name: String,
    val description: String
)

@Serializable
data class Performer(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String
)
