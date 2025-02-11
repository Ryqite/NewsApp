package com.example.modsen.DomainLayer.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modsen.DataLayer.Resource
import com.example.modsen.DataLayer.newsResponse
import com.example.modsen.DomainLayer.NewsRepository
import kotlinx.coroutines.launch
import okhttp3.Response

class NewsViewModel(val newsRepository: NewsRepository): ViewModel() {
    val allNews: MutableLiveData<Resource<newsResponse>> = MutableLiveData()
    val allNewPage = 1
    init {
        getAllNews("us")
    }
    fun getAllNews(countryCode: String) = viewModelScope.launch{

        val response= newsRepository.getNews(countryCode,allNewPage)
        allNews.postValue(handldeAllNewsResponse(response))

    }
    private fun handldeAllNewsResponse(response: retrofit2.Response<newsResponse>): Resource<newsResponse>{
        if(response.isSuccessful){
            response.body()?.let {resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Failure(response.message())
    }
}