package com.example.modsen.DomainLayer.Retrofit

import retrofit2.http.Query
import com.example.modsen.DataLayer.Constants.Companion.API_KEY
import com.example.modsen.DataLayer.newsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String="us",
        @Query("page")
        pageNumber:Int=1,
        @Query("apiKey")
        apiKey: String=API_KEY
    ): Response<newsResponse>
    @GET("v2/top-headlines")
    suspend fun getCategoryNews(
        @Query("country")
        countryCode: String="us",
        @Query("page")
        pageNumber:Int=1,
        @Query("category")
        category: String,
        @Query("apiKey")
        apiKey: String=API_KEY
    ): Response<newsResponse>
}