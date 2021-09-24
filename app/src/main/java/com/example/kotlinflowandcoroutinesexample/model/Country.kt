package com.example.kotlinflowandcoroutinesexample.model

import com.google.gson.annotations.SerializedName


 class Country{

     @SerializedName("name")
     var name: String = ""

     @SerializedName("capital")
     var capital: String = ""

     @SerializedName("region")
     var region: String = ""

     @SerializedName("flag")
     var flag: String = ""

     @SerializedName("callingCodes")
      var callingCodes: ArrayList<String>? = null

     @SerializedName("latlng")
     var latlng: ArrayList<String>? = null
 }