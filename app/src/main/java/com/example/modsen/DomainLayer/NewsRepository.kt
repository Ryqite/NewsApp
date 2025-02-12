package com.example.modsen.DomainLayer

import com.example.modsen.DataLayer.Article
import com.example.modsen.DomainLayer.DB.ArticleDataBase
import com.example.modsen.DomainLayer.Retrofit.RetrofitInstance

class NewsRepository(val db: ArticleDataBase) {
    suspend fun getNews(countryCode: String,pageNumber: Int)=
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)
    suspend fun saveArticle(article: Article)= db.getArticleDao().upsert(article)
    fun getBookmarks()=db.getArticleDao().getAllArticles()
    suspend fun deleteArticle(article: Article)=db.getArticleDao().deleteArticle(article)
}