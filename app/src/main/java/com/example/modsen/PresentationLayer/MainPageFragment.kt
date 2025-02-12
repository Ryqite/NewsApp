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

class MainPageFragment : Fragment(R.layout.fragment_main_page) {

    lateinit var binding: FragmentMainPageBinding
    lateinit var newsAdapter: NewsAdapter
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
            viewModel.allNews.observe(viewLifecycleOwner, Observer { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { newsResponse ->
                            newsAdapter.differ.submitList(newsResponse.articles)
                        }
                    }

                    is Resource.Failure -> {
                        response.message?.let { message ->
                            Log.e(
                                TAG,
                                "Error: АААААААААААААААААААААААААААААААААААААААААААААААААААААААААААА"
                            )
                        }
                    }

                    else -> {

                    }

                }
            })
            val controller = findNavController()
            binding.bottommenu.selectedItemId = R.id.mainPageFragment
            binding.bottommenu.setupWithNavController(controller)
        }
    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        binding.mainPageRV.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)}
    }
    }