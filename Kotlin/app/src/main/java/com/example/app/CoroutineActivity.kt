package com.example.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.app.http.Api
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CoroutineActivity :AppCompatActivity() {
//    private val scope = MainScope()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        /*GlobalScope.launch (Dispatchers.Main){
            println(Thread.currentThread().name)
            val one = async {  }
            val two = async {  }
            val same  = one.await()
        }*/
        /*GlobalScope.launch(Dispatchers.Main){
            ioCode1()
            uiCode1()
            ioCode2()
            uiCode2()
            ioCode3()
            uiCode3()
        }*/

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(Api::class.java)
        /*GlobalScope.launch (Dispatchers.Main){retrofit.create
            val result = api.listRepoKT("octocat")
            text.text = result[0].name
        }*/
        lifecycleScope.launch (Dispatchers.Main){
            val one  = async {  api.listRepoKT("rengwuxian")}
            val two = async {api.listRepoKT("octocat")}
            val result = one.await()[0].name == two.await()[0].name
            text.text= result.toString()

        }


    }

    private suspend fun ioCode1(){
        withContext(Dispatchers.IO){
//            Thread.sleep(1000)
            println("ioCode1：${Thread.currentThread().name}")
        }
    }
    private suspend fun ioCode2(){
        withContext(Dispatchers.IO){
//            Thread.sleep(1000)

            println("ioCode2：${Thread.currentThread().name}")
        }
    }
    private suspend fun ioCode3(){
        withContext(Dispatchers.IO){
            Thread.sleep(1000)
            println("ioCode3：${Thread.currentThread().name}")
        }
    }

    private fun uiCode1(){
        println("uiCode1: ${Thread.currentThread().name}")
    }
    private fun uiCode2(){
        println("uiCode2: ${Thread.currentThread().name}")

    }
    private fun uiCode3(){
        println("uiCode3: ${Thread.currentThread().name}")
    }


}