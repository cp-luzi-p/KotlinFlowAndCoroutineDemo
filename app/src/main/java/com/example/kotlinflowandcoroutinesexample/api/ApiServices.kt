package com.example.kotlinflowandcoroutinesexample.api

import com.example.kotlinflowandcoroutinesexample.model.Country
import retrofit2.http.GET

interface ApiServices {

    @GET("all")
    suspend fun getCountry(): List<Country>
}