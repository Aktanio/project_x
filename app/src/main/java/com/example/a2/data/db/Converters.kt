package com.example.a2.data.db

import androidx.room.TypeConverter
import com.example.a2.data.CountryResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromStringList(list: List<String>?): String?{
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(data: String?): List<String>?{
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun fromMap(map: Map<String, String>?): String?{
        return gson.toJson(map)
    }

    @TypeConverter
    fun toMap(data: String?): Map<String, String>?{
        val type = object : TypeToken<Map<String, String>>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun fromNativeName(map: Map<String, CountryResponse.NativeName>?): String?{
        return gson.toJson(map)
    }

    @TypeConverter
    fun toNativeName(data: String?): Map<String, CountryResponse.NativeName>?{
        val type = object : TypeToken<Map<String, CountryResponse.NativeName>>() {}.type
        return gson.fromJson(data, type)
    }
}