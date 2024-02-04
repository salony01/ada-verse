package com.example.adaverse.model

data class Content(
    val aspectRatio: String,
    val description: String,
    val genre: List<String>,
    val id: String,
    val providers: List<Provider>,
    val rating: Double,
    val releaseYear: Int,
    val showType: String,
    val thumbnail: String,
    val contentUrl: String,
    val title: String
)