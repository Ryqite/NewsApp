package com.example.modsen.PresentationLayer

import com.example.modsen.DomainLayer.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import com.example.modsen.DataLayer.NewsItem
import com.example.modsen.R
import com.example.modsen.databinding.FragmentDetailPageBinding


class DetailPageFragment : Fragment() {
    lateinit var binding: FragmentDetailPageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_page,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db= DataBase.getDB(requireContext())
        db.getDao().getNewsItems().asLiveData().observe(requireContext() as LifecycleOwner){
            TODO()
        }
        binding.bookmarksbtn.setOnClickListener{
            TODO()
            val newsItem= NewsItem(null,
                binding.newsItemName.text.toString(),
                binding.newsItemSource.text.toString(),
                binding.newsItemDescription.text.toString())
            if(newsItem.isBookmark==false) {
                db.getDao().insetNewsItem(newsItem)
                newsItem.isBookmark=true
            }
            else{
                db.getDao().deleteNewsItem(newsItem)
                newsItem.isBookmark=false
            }
        }
    }
}