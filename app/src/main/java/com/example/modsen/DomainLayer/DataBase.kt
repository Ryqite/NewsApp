package com.example.modsen.DomainLayer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.modsen.DataLayer.NewsItem

@Database(entities = [NewsItem::class], version = 1)
abstract class DataBase: RoomDatabase() {
    abstract fun getDao(): Dao
    companion object{
        fun getDB(context: Context): DataBase{
            return Room.databaseBuilder(
                context.applicationContext,
                DataBase::class.java,
                "NewsDB")
                .build()
        }
    }
}