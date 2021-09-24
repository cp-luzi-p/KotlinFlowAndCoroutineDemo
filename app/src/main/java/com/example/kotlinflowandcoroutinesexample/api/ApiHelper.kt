package com.example.kotlinflowandcoroutinesexample.api

class ApiHelper (private val apiServices: ApiServices) {

    suspend fun getCountry() = apiServices.getCountry()
}