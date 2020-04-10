package com.example.app

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{user}/repos")
    fun listRespos(@Path("user") user: String?): Call<List<Respo>>

    @GET("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=520479455,1513962327&fm=26&gp=0.jpg")
    fun getimageBitmap () :Call<ResponseBody>

}