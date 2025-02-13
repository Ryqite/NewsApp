package com.example.modsen.PresentationLayer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modsen.DataLayer.Resource
import com.example.modsen.DomainLayer.Adapters.NewsAdapter
import com.example.modsen.DomainLayer.ViewModel.NewsViewModel
import com.example.modsen.R
import com.example.modsen.databinding.FragmentMainPageBinding
import com.google.android.material.tabs.TabLayout

class MainPageFragment : Fragment(R.layout.fragment_main_page) {
    companion object {
    var category: String="sports"
    }
    lateinit var binding: FragmentMainPageBinding
    lateinit var newsAdapter: NewsAdapter
    var _category: String=""
    private val viewModel: NewsViewModel by activityViewModels()
    val TAG = "MainPageFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
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
                R.id.action_mainPageFragment_to_detailPageFragment,bundle
            )
        }
        allNews()
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    _category=when(it.position){
                        1 -> "sports"
                        2 -> "health"
                        else -> "all"
                    }
                    if(_category!="all"){
                        categoryNews()
                    }
                    else{
                        allNews()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.let {
                    _category=when(it.position){
                        1 -> "sports"
                        2 -> "health"
                        else -> "all"
                    }
                    if(_category!="all"){
                        categoryNews()
                    }
                    else{
                        allNews()
                    }
                }
            }
        })

            val controller = findNavController()
            binding.bottommenu.selectedItemId = R.id.mainPageFragment
            binding.bottommenu.setupWithNavController(controller)
        }
    fun allNews(){
        viewModel.allNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Failure -> {
                    response.message?.let { message ->
                        Log.e(TAG, "Error: Failure")
                    }
                }
                else -> {}
            }
        })
    }
    fun categoryNews(){
        category=_category
        viewModel.categoryNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Failure -> {
                    response.message?.let { message ->
                        Log.e(TAG, "Error: Failure")
                    }
                }
                else -> {}
            }
        })
    }
    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        binding.mainPageRV.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)}
    }
    }