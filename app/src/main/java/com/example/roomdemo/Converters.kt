package com.example.roomdemo

import androidx.room.TypeConverter
import com.example.roomdemo.db.FootballClub
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

    @TypeConverter
    fun fromFootballToString(footballClub: FootballClub): String {
        val gson = Gson()
        return gson.toJson(footballClub)
    }

    @TypeConverter
    fun fromStringToFootball(string: String): FootballClub {
        val footballType: Type = object : TypeToken<FootballClub?>() {}.type
        return Gson().fromJson(string, footballType)
    }

    @TypeConverter
    fun fromAnyToString(any: Any): String {
        return any.toString()
    }

    @TypeConverter
    fun fromStringToAny(string: String): Any {
        val anyType: Type = object : TypeToken<Any?>() {}.type
        return Gson().fromJson(string, anyType)
    }

}