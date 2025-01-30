package com.example.modsen.DomainLayer.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import com.example.modsen.DomainLayer.NewsRepository
import kotlin.concurrent.thread

class NewsViewModelProviderFactory(
    val newsRepository: NewsRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}