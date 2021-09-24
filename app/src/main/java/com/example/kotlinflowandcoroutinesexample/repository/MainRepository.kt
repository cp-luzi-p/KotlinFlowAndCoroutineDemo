package com.example.kotlinflowandcoroutinesexample.repository

import com.example.kotlinflowandcoroutinesexample.api.ApiHelper

class MainRepository (private val apiHelper: ApiHelper) {
    suspend fun getCountryList() = apiHelper.getCountry()
}