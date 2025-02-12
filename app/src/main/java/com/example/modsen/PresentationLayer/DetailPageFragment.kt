package com.example.modsen.PresentationLayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.navArgs
import com.example.modsen.DomainLayer.DB.DataBase
import com.example.modsen.DomainLayer.ViewModel.NewsViewModel
import com.example.modsen.databinding.FragmentDetailPageBinding
import com.google.android.material.snackbar.Snackbar
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
        binding.bookmarksbtn.setOnClickListener{
            if(article.isSaved==false) {
                viewModel.saveArticle(article)
                article.isSaved=true
                Snackbar.make(view,"Article saved", Snackbar.LENGTH_SHORT).show()
            }
            else{
                viewModel.deleteArticle(article)
                article.isSaved=false
                Snackbar.make(view,"Article deleted", Snackbar.LENGTH_SHORT).apply {
                    setAction("Undo") {
                        viewModel.saveArticle(article)
                        article.isSaved=true
                    }
                    show()
                }
            }
        }
    }
}