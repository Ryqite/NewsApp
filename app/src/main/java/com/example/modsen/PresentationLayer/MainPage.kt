package com.example.modsen.PresentationLayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.modsen.R

class MainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainpage)
        setupActionBarWithNavController(findNavController(R.id.fragmentContainerView))
    }

    override fun onSupportNavigateUp(): Boolean {
        val controller=findNavController(R.id.fragmentContainerView)
        return controller.navigateUp() || super.onSupportNavigateUp()
    }
}