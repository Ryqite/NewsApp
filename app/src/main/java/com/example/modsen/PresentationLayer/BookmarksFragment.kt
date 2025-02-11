package com.example.modsen.PresentationLayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modsen.DomainLayer.Adapters.NewsAdapter
import com.example.modsen.DomainLayer.ViewModel.NewsViewModel
import com.example.modsen.R
import com.example.modsen.databinding.FragmentBookmarksBinding
import kotlin.getValue

class BookmarksFragment : Fragment() {
    private val viewModel: NewsViewModel by activityViewModels()
    lateinit var binding: FragmentBookmarksBinding
    lateinit var newsAdapter: NewsAdapter
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
        setupRecyclerView()
        newsAdapter.setOnItemClickListener {
            val bundle= Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(
                R.id.action_bookmarksFragment_to_detailPageFragment,bundle
            )
        }
        val controller=findNavController()
        binding.bottommenu.setupWithNavController(controller)/*{
            when(it.itemId){
                R.id.allnews -> {controller.navigate(R.id.mainPageFragment)}
                R.id.bookmarks -> {controller.navigate(R.id.bookmarksFragment)}
            }
            true
        }*/
    }
    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        binding.RVBookmarks.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)}
    }
}