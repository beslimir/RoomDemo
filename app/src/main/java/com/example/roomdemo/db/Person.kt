package com.example.roomdemo.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    var name: String,
    var lastName: String,
    var years: Int,
    var brothersSisters: List<String>?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
