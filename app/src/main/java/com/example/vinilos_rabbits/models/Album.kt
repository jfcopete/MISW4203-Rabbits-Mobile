package com.example.vinilos_rabbits.models

import kotlinx.serialization.Serializable

@Serializable
data class Album (
    val albumId: Int? = null,
    val id: Int? = null,
    val name:String,
    val cover:String,
    val releaseDate:String,
    val description:String,
    val genre:String,
    val recordLabel:String,
    val comments: MutableList<Comment> = mutableListOf()
)