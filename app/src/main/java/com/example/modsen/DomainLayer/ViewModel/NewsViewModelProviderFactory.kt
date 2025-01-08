package com.example.modsen.DomainLayer.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import com.example.modsen.DomainLayer.NewsRepository

class NewsViewModelProviderFactory(
    val newsRepository: NewsRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}