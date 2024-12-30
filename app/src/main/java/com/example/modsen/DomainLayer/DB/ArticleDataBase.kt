package com.example.modsen.DomainLayer.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.modsen.DataLayer.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converter::class)
abstract class ArticleDataBase: RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao
    companion object{
    @Volatile
    private var instance: ArticleDataBase?=null
        private val Lock= Any()
        operator fun invoke(context: Context)=instance?:synchronized(Lock){
            instance?: createDatabase(context).also {instance=it}
        }
        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDataBase::class.java,
                "artcile_db.db"
            ).build()
    }
}