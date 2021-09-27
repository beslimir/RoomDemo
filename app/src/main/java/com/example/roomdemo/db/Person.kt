package com.example.roomdemo.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    var name: String,
    var lastName: String,
    var years: Int,
    val brothersSisters: List<String>?,
    val footballClub: FootballClub,
    val city: Any
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
