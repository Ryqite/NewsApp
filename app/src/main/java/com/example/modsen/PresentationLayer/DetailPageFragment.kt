package com.example.modsen.PresentationLayer

import com.example.modsen.DomainLayer.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.navArgs
import com.example.modsen.DataLayer.NewsItem
import com.example.modsen.DomainLayer.DB.DataBase
import com.example.modsen.DomainLayer.ViewModel.NewsViewModel
import com.example.modsen.R
import com.example.modsen.databinding.FragmentDetailPageBinding
import com.example.modsen.databinding.FragmentMainPageBinding
import kotlin.getValue


class DetailPageFragment : Fragment() {
    lateinit var binding: FragmentDetailPageBinding
    private val viewModel: NewsViewModel by activityViewModels()
    val args: DetailPageFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailPageBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article=args.article
        binding.webView.apply {
            webViewClient= WebViewClient()
            loadUrl(article.url)
        }
        val db= DataBase.getDB(requireContext())
        db.getDao().getNewsItems().asLiveData().observe(requireContext() as LifecycleOwner){
            //
        }
//        binding.bookmarksbtn.setOnClickListener{
//            //
//            val newsItem= NewsItem(null,
//                binding.newsItemName.text.toString(),
//                binding.newsItemSource.text.toString(),
//                binding.newsItemDescription.text.toString())
//            if(newsItem.isBookmark==false) {
//                db.getDao().insetNewsItem(newsItem)
//                newsItem.isBookmark=true
//            }
//            else{
//                db.getDao().deleteNewsItem(newsItem)
//                newsItem.isBookmark=false
//            }
//        }
    }
}