package com.example.modsen.DomainLayer.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modsen.DataLayer.Article
import com.example.modsen.DataLayer.Resource
import com.example.modsen.DataLayer.newsResponse
import com.example.modsen.DomainLayer.NewsRepository
import com.example.modsen.PresentationLayer.MainPageFragment.Companion.category
import kotlinx.coroutines.launch

class NewsViewModel(val newsRepository: NewsRepository): ViewModel() {
    val allNewPage = 1
    val allNews: MutableLiveData<Resource<newsResponse>> by lazy {
        MutableLiveData<Resource<newsResponse>>().also {
            getAllNews("us")
        }
    }
    val categoryNews: MutableLiveData<Resource<newsResponse>> by lazy {
        MutableLiveData<Resource<newsResponse>>().also{
            getCategoryNews("us", category)
        }
    }
    fun getAllNews(countryCode: String) = viewModelScope.launch{

        val response= newsRepository.getNews(countryCode,allNewPage)
        allNews.postValue(handldeAllNewsResponse(response))
    }
    fun getCategoryNews(countryCode: String,category: String) = viewModelScope.launch{

        val response= newsRepository.getSport(countryCode,allNewPage,category)
        categoryNews.postValue(handldeAllNewsResponse(response))
    }
    private fun handldeAllNewsResponse(response: retrofit2.Response<newsResponse>): Resource<newsResponse>{
        if(response.isSuccessful){
            response.body()?.let {resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Failure(response.message())
    }
    fun saveArticle(article: Article)=viewModelScope.launch{
        newsRepository.saveArticle(article)
    }
    fun getBookmarks()=newsRepository.getBookmarks()
    fun deleteArticle(article: Article)=viewModelScope.launch{
        newsRepository.deleteArticle(article)
    }
}