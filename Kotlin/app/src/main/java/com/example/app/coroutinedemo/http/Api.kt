package com.example.app.http

import com.example.app.Respo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("users/{user}/repos")
   suspend fun listRepoKT(@Path("user") user:String ) : List<Respo>
}