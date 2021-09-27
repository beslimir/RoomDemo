package com.example.roomdemo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdemo.Constants.Companion.LOG_TAG
import com.example.roomdemo.db.MainRepository
import com.example.roomdemo.db.Person
import kotlinx.coroutines.launch

class MainViewModel(val mainRepository: MainRepository): ViewModel() {

    fun upsertPerson(person: Person) = viewModelScope.launch {
        mainRepository.upsertPerson(person)
        Log.d(LOG_TAG, "Thread: ${Thread.currentThread().name}")
    }


}