package com.example.modsen.PresentationLayer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modsen.DataLayer.Resource
import com.example.modsen.DataLayer.newsResponse
import com.example.modsen.DomainLayer.Adapters.NewsAdapter
import com.example.modsen.DomainLayer.DB.ArticleDataBase
import com.example.modsen.DomainLayer.DB.ArticleDataBase.Companion
import com.example.modsen.DomainLayer.NewsRepository
import com.example.modsen.DomainLayer.ViewModel.NewsViewModel
import com.example.modsen.DomainLayer.ViewModel.NewsViewModelProviderFactory
import com.example.modsen.R
import com.example.modsen.databinding.FragmentMainPageBinding
import kotlinx.coroutines.delay

class MainPageFragment : Fragment(R.layout.fragment_main_page) {

    lateinit var binding: FragmentMainPageBinding
    lateinit var newsAdapter: NewsAdapter
    private val viewModel: NewsViewModel by activityViewModels()
    val TAG="MainPageFragment"
    //private val viewModel: NewsViewModel by activityViewModels()
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
        viewModel.allNews.observe(viewLifecycleOwner, Observer{response->
            when(response){
                is Resource.Success->{
                    response.data?.let { newsResponse->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Failure->{
                    response.message?.let{message->
                        Log.e(TAG,"Error: АААААААААААААААААААААААААААААААААААААААААААААААААААААААААААА")
                    }
                }
                is Resource.Loading->{

                }
            }
        })
        val controller=findNavController()
        binding.bottommenu.selectedItemId=R.id.allnews
        binding.bottommenu.setupWithNavController(controller)/*{
            when(it.itemId){
                R.id.allnews -> {controller.navigate(R.id.mainPageFragment)}
                R.id.bookmarks -> {controller.navigate(R.id.bookmarksFragment)}
            }
            true
        }*/

    }

    private fun setupRecyclerView(){
        newsAdapter= NewsAdapter()
        binding.mainPageRV.apply{
            adapter=newsAdapter
            layoutManager= LinearLayoutManager(activity)
        }
    }
}