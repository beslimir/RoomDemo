package com.example.roomdemo.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdemo.MainViewModel

class MainViewModelFactory(val mainRepository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(mainRepository) as T
    }
}