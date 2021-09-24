package com.example.kotlinflowandcoroutinesexample.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinflowandcoroutinesexample.ui.main.MainViewModel
import com.example.kotlinflowandcoroutinesexample.api.ApiHelper
import com.example.kotlinflowandcoroutinesexample.repository.MainRepository
import java.lang.IllegalArgumentException

class ViewModelFactory (private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}