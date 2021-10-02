package com.example.roomdemo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdemo.Constants.Companion.LOG_TAG
import com.example.roomdemo.db.Person
import kotlinx.coroutines.launch

class MainViewModel(val mainRepository: MainRepository) : ViewModel() {

    lateinit var allPeople: MutableLiveData<List<Person>>

    init {
        listAllPeople()
    }

    fun upsertPerson(person: Person) = viewModelScope.launch {
        mainRepository.upsertPerson(person)
        Log.d(LOG_TAG, "updatePerson thread: ${Thread.currentThread().name}")
    }

    fun listAllPeople() = mainRepository.listAllPeople()

    fun deletePerson(person: Person) = viewModelScope.launch {
        mainRepository.deletePerson(person)
        Log.d(LOG_TAG, "Person ${person.name} deleted!")
    }


}