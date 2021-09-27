package com.example.roomdemo.db

class MainRepository(val mainDb: MainDatabase) {

    suspend fun upsertPerson(person: Person) = mainDb.getMainDao().upsertPerson(person)

    suspend fun deletePerson(person: Person) = mainDb.getMainDao().deletePerson(person)

    fun listAllPeople() = mainDb.getMainDao().listAllPeople()
}