package com.example.roomdemo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdemo.Constants.Companion.LOG_TAG
import com.example.roomdemo.db.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(val mainRepository: MainRepository): ViewModel() {

    lateinit var allPeople: MutableLiveData<List<Person>>

    init {
        listAllPeople()
    }

    fun upsertPerson(person: Person) = viewModelScope.launch(Dispatchers.IO) {
        mainRepository.upsertPerson(person)
        Log.d(LOG_TAG, "updatePerson thread: ${Thread.currentThread().name}")
    }

    //TODO: Implement with differUtil
    fun listAllPeople() = mainRepository.listAllPeople()


}