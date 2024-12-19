package com.example.modsen.DataLayer

import java.io.Serializable

data class NewsItem(
    val name: String,
    val source: String,
    val description: String,
    val isBookmark: Boolean
): Serializable