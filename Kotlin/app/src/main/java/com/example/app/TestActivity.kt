package com.example.app

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.app.entity.User
import com.example.core.utils.CacheUtils.context
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.CoroutineContext

class TestActivity : AppCompatActivity() {
    lateinit var iv_image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iv_image = findViewById(R.id.iv_image)
        var retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        var service = retrofit.create(GithubService::class.java)

        var repos = service.listRespos("octocat")

        repos.enqueue(object : Callback<List<Respo>?> {
            override fun onFailure(call: Call<List<Respo>?>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Respo>?>, response: Response<List<Respo>?>) {
                println("onResponse:${response.body()!![0].name}")
            }
        })

//        var (Request)->Response

        var url = "https://api.github.com/"

        var okHttpClient = OkHttpClient()
        val request = okhttp3.Request.Builder()
                .url(url)
                .build()
        okHttpClient.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
            }
        })

        var intarray = intArrayOf(1, 2, 3)
        var array = intarray.filter { i -> i == 1 }.toMutableList()
        for (a: Int in array) {
            println("array==$a")
        }


        val sequence = sequenceOf(1, 2, 3, 4)
        val result = sequence
                .map { i ->
                    println("Map $i")
                    i * 2
                }
                .filter { i ->
                    println("Filter $i")
                    i % 3 == 0
                }

        println(result.first()) // 👈 只取集合的第一个元素

        val list = listOf(1, 2, 3, 4)

        val result2 = list
                .map { i ->
                    println("Map $i")
                    i * 2
                }
                .filter { i ->
                    println("Filter $i")
                    i % 3 == 0
                }

        println("Don=====" + result.first())


        val list2 = mutableListOf(21, 40, 11, 33, 78)
        val list3 = list2.filter { i ->
            i % 3 == 0
        }
        println("Donn==== ${list3}")


//        xiecheng()
        xiecheng2()
    }

    fun test(listener: (Request) -> okhttp3.Response) {

    }

    var call = ::test


    var user = User()


    fun <T> fill(ar: Array<in T>, t: T) {
//        ar.set(user)
        ar[0] = t

//        ar.set
    }
//    var array = Array<Int>(1,3)


    fun xiecheng() {
        GlobalScope.launch {
            delay(1000L) // 非阻塞式地延迟一秒
            println("World!") // 延迟结束后打印
        }
        println("Hello,") //主线程继续执行，不受协程 delay 所影响
        Thread.sleep(2000L) // 主线程阻塞式睡眠2秒，以此来保证JVM存活
/*
        val coroutineScope = CoroutineScope(context)

        coroutineScope.launch(Dispatchers.Main){

        }
        coroutineScope.launch(Dispatchers.IO){

        }

        coroutineScope.launch(Dispatchers.Main) {
            //            👇  async 函数之后再讲
            val avatar = async {
                api.getAvatar(user) }    // 获取用户头像
            val logo = async {
                api.getCompanyLogo(user)
            } // 获取用户所在公司的 logo
            val merged = suspendingMerge(avatar, logo)    // 合并结果
            //                  👆
            show(merged) // 更新 UI

        }

        coroutineScope.launch(Dispatchers.Main) {      // 👈 在 UI 线程开始
            val image = withContext(Dispatchers.IO) {  // 👈 切换到 IO 线程，并在执行完成后切回 UI 线程
                getImage(imageId)                      // 👈 将会运行在 IO 线程
            }
            avatarIv.setImageBitmap(image)             // 👈 回到 UI 线程更新 UI
        }*/
    }


    fun xiecheng2() {
        GlobalScope.launch(Dispatchers.Main) {
            println("当前线程=${Thread.currentThread().name}")
            withContext(Dispatchers.IO) {
                println("当前线程2=${Thread.currentThread().name}")

            }
            println("当前线程3=${Thread.currentThread().name}")

        }

        CoroutineScope(Dispatchers.Main).launch {
            val bitmap = getImage2()
            iv_image.setImageBitmap(bitmap)
        }

    }

    suspend fun getImage(): Bitmap {
        return withContext(Dispatchers.IO) {
            val imagestr = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=520479455,1513962327&fm=26&gp=0.jpg"

            val connection = URL(imagestr).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()
            val inputStream = connection.inputStream
            return@withContext BitmapFactory.decodeStream(inputStream)
        }
    }

    suspend fun getImage2(): Bitmap {

        return withContext(Dispatchers.IO) {

            val imagestr = "https://ss2.bdstatic.com/"

            val call = Retrofit.Builder().baseUrl(imagestr).build()
            val service = call.create(GithubService::class.java)
            val response = service.getimageBitmap().execute()
            val inputStream = response.body()?.byteStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            return@withContext bitmap
        }
    }



}