package com.example.modsen.DataLayer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "News")
data class NewsItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    @ColumnInfo(name="name")
    val name: String,
    @ColumnInfo(name="name")
    val source: String,
    @ColumnInfo(name="description")
    val description: String,
    @ColumnInfo(name="isBookmark")
    var isBookmark: Boolean=false
): Serializable