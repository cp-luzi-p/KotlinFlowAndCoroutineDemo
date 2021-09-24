package com.example.kotlinflowandcoroutinesexample.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kotlinflowandcoroutinesexample.repository.MainRepository
import com.example.kotlinflowandcoroutinesexample.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel (private val mainRepository: MainRepository) : ViewModel() {

    fun getCountry() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getCountryList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}