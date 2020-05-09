package com.example.app.httpurlcondemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import kotlinx.android.synthetic.main.activity_test.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class HttpUrlDemo :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        sendHttpUrlConnetion()

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

    private fun showResponse(response: String) {
        runOnUiThread {
            Log.i("Don", "sendHttpUrlConnetion: ${response.toString()}")
            text.text = response
        }
    }
}