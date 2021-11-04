package com.example.roomdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdemo.model.Person

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertPerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query("SELECT * FROM Person")
    fun listAllPeople(): LiveData<List<Person>>

}