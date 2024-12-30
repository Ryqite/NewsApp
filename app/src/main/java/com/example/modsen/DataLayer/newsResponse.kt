package com.example.modsen.DataLayer

data class newsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)