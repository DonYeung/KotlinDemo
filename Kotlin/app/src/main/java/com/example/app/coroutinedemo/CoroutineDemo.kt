package com.example.app.coroutinedemo

import com.example.app.GithubService
import com.example.app.Respo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.RuntimeException
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CoroutineDemo {


    suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine {
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) it.resume(body)
                    else it.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    it.resumeWithException(t)
                }
            })
        }
    }

    var retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()

    var service = retrofit.create(GithubService::class.java)


    private fun nomal() {

        service.listRespos("octocat").enqueue(object : Callback<List<Respo>?> {
            override fun onResponse(call: Call<List<Respo>?>, response: Response<List<Respo>?>) {
                //get成功
            }
            override fun onFailure(call: Call<List<Respo>?>, t: Throwable) {
                //get失败
            }


        })
    }

    suspend fun getData() {
        try {
            val a = service.listRespos("octocat").await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun main() {
        val myDispatcher= Executors.newSingleThreadExecutor{
            Thread(it, "MyThread") }.asCoroutineDispatcher()
        GlobalScope.launch(myDispatcher) {

        }.join()
    }
}