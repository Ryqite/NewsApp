package com.example.modsen.DomainLayer.DB

import androidx.room.TypeConverter
import com.example.modsen.DataLayer.Source

class Converter {
    @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }
    @TypeConverter
    fun toSource(name: String): Source{
        return Source(name,name)
    }
}