package com.example.roomdemo

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(string: String): List<String> {
//        val listType = TypeToken<List<String>>() {}.type
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(string, listType)
    }

//    @TypeConverter
//    fun fromString(stringListString: String): List<String> {
//        return stringListString.split(",").map { it }
//    }
//
//    @TypeConverter
//    fun toString(stringList: List<String>): String {
//        return stringList.joinToString(separator = ",")
//    }

}