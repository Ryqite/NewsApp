package com.example.modsen.DomainLayer

import com.example.modsen.DomainLayer.DB.ArticleDataBase
import com.example.modsen.DomainLayer.Retrofit.RetrofitInstance

class NewsRepository(val db: ArticleDataBase) {
    suspend fun getNews(countryCode: String,pageNumber: Int)=
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)
}