package com.example.modsen.DomainLayer.DB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.modsen.DataLayer.NewsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insetNewsItem(item: NewsItem)
    @Query("SELECT * FROM news")
    fun getNewsItems(): Flow<List<NewsItem>>
    @Delete
    fun deleteNewsItem(item: NewsItem)
}