package com.example.modsen.PresentationLayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.modsen.R
import com.example.modsen.databinding.FragmentMainPageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainpage)

//        val nav=findNavController(R.id.fragmentContainerView)
//        val menu= binding.bottommenu
//        NavigationUI.setupWithNavController(menu,nav)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val controller=findNavController(R.id.fragmentContainerView)
//        return controller.navigateUp() || super.onSupportNavigateUp()
//    }
}