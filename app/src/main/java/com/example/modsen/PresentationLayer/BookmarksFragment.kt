package com.example.modsen.PresentationLayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.modsen.R
import com.example.modsen.databinding.FragmentBookmarksBinding

class BookmarksFragment : Fragment() {
    lateinit var binding: FragmentBookmarksBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller=findNavController()
        binding.bottommenu.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.allnews -> {controller.navigate(R.id.mainPageFragment)}
                R.id.bookmarks -> {controller.navigate(R.id.bookmarksFragment)}
            }
            true
        }
    }
}