package com.example.app.coroutinedemo.http

import com.example.app.HourData
import com.example.app.Respo
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("users/{user}/repos")
   suspend fun listRepoKT(@Path("user") user:String ) : List<Respo>

    @GET("https://api.caiyunapp.com/v2.5/6FftPIsi3YC5frUy/121.6544,25.1552/hourly.json")
    suspend fun listHourData() :HourData
}