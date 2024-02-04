package com.example.adaverse.model

data class LivetvRecommendation(
    val aspectRatio: String,
    val description: String,
    val genres: List<String>,
    val id: String,
    val network: String,
    val network_thumbnail: String,
    val reason: String,
    val showType: String,
    val show_thumbnail: String,
    val show_time_window: ShowTimeWindow,
    val title: String
)