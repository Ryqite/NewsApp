package com.example.modsen.PresentationLayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.modsen.DomainLayer.DB.ArticleDataBase
import com.example.modsen.DomainLayer.NewsRepository
import com.example.modsen.DomainLayer.ViewModel.NewsViewModel
import com.example.modsen.DomainLayer.ViewModel.NewsViewModelProviderFactory
import com.example.modsen.databinding.MainpageBinding

class MainPage : AppCompatActivity() {
    lateinit var viewModel: NewsViewModel
    lateinit var binding: MainpageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainpageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = NewsRepository(ArticleDataBase.getInstance(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]
    }
}