package com.example.app.httpdemo

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import com.example.app.R
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class HttpUrlDemo :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
//        sendHttpUrlConnetion()
//        sendOkhttp()
        sendHttpWithCoroutine()

    }
    private fun sendHttpUrlConnetion() {
        val response = StringBuilder()
        thread {
            val url = URL("https://www.baidu.com")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            val input = connection.inputStream
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    response.append(it)
                }
            }
            showResponse(response.toString())
            connection.disconnect()
        }



    }
    private fun sendOkhttp(){
//        val response = StringBuilder()
        thread {
            val client = OkHttpClient()
            val request = Request.Builder().url("https://www.baidu.com").build()
            val res = client.newCall(request).execute()
            showResponse( res.body?.string())
        }

    }
    private fun sendHttpWithCoroutine(){
        lifecycleScope.launch(Dispatchers.Main) {
            val respone = sendResquest()
            text.text = respone
            Log.i("Don", "sendHttpWithCoroutine: ")

        }
    }
    private suspend fun sendResquest():String?=
         withContext(Dispatchers.IO){
            val client = OkHttpClient()
            val request = Request.Builder().url("https://www.baidu.com").build()
            val respone = client.newCall(request).execute()
            return@withContext respone.body?.string()
        }



    private fun showResponse(response: String?) {
        runOnUiThread {
            Log.i("Don", "sendHttpUrlConnetion: ${response.toString()}")
            text.text = response
        }
    }
}