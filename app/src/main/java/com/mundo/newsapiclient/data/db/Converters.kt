package com.mundo.newsapiclient.data.db

import androidx.room.TypeConverter
import com.mundo.newsapiclient.data.model.Source

class Converters {
    @TypeConverter
    fun fromSource(source: Source):String {
        return source.name.toString()
    }

    @TypeConverter
    fun toSource(name: String): Source{
        return Source(name,name)
    }
}