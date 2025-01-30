package com.example.modsen.PresentationLayer

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.modsen.DomainLayer.DB.ArticleDataBase
import com.example.modsen.DomainLayer.NewsRepository
import com.example.modsen.DomainLayer.ViewModel.NewsViewModel
import com.example.modsen.DomainLayer.ViewModel.NewsViewModelProviderFactory
import com.example.modsen.R
import com.example.modsen.databinding.FragmentMainPageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainPage : AppCompatActivity() {
    lateinit var viewModel: NewsViewModel
    lateinit var binding: FragmentMainPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository= NewsRepository(ArticleDataBase.getInstance(this))
        val viewModelProviderFactory= NewsViewModelProviderFactory(repository)
        viewModel= ViewModelProvider(this,viewModelProviderFactory)[NewsViewModel::class.java]

//        val nav=findNavController(R.id.fragmentContainerView)
//        val menu= binding.bottommenu
//        NavigationUI.setupWithNavController(menu,nav)
    }


//    override fun onSupportNavigateUp(): Boolean {
//        val controller=findNavController(R.id.fragmentContainerView)
//        return controller.navigateUp() || super.onSupportNavigateUp()
//    }
}