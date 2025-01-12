package com.example.modsen.DomainLayer.Retrofit

import retrofit2.http.Query
import com.example.modsen.DataLayer.Constants.Companion.API_KEY
import com.example.modsen.DataLayer.newsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {
    @GET("v2/everything")
    suspend fun getAllNews(
        @Query("country")
        countryCode: String="ru",
        @Query("page")
        pageNumber:Int=1,
        @Query("apiKey")
        apiKey: String=API_KEY
    ): Response<newsResponse>
}