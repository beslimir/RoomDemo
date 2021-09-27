package com.example.roomdemo.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var lastName: String,
    var years: Int
)
