package com.example.vinilos_rabbits.services

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
//import kotlinx.serialization.Optional


@Serializable
data class PerformerSerialized (
    val id: Int,
    val name: String,
    val image: String,
    val description: String?,
    val birthDate: String? = null,
    val creationDate: String? = null
)

val json = Json { ignoreUnknownKeys = true }