package com.example.vinilos_rabbits.models

import kotlinx.serialization.Serializable

@Serializable
data class Collector (
    val id: Int,
    val name:String,
    val telephone:String,
    val email:String
)
