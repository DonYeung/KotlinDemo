package com.example.app.filewritedemo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Display
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import kotlinx.android.synthetic.main.activity_constaint2.*
import java.io.*
import java.lang.StringBuilder

class FileWriteDemo :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constaint2)
        btn1.setOnClickListener {
//            save()

            load()
        }
    }

    private fun save() {
        val data = "hahahahhahahahahahdwqqeqwewqewqeqwqewq"
        val ous = openFileOutput("data", Context.MODE_PRIVATE)
        val bufferedWriter = BufferedWriter(OutputStreamWriter(ous))
//        val bufferedWriter2 = BufferedOutputStream(ous)
//        bufferedWriter2
        bufferedWriter.use {
            bufferedWriter.write(data)
        }
    }


    private fun load(){
        val content =StringBuilder()
        content?.append("adasda")

        val ins = openFileInput("data")
        val bufferedReader = BufferedReader(InputStreamReader(ins))
        bufferedReader.use {
            it.forEachLine {
                content?.append(it)
            }
        }
        println("content:::${content}")
    }
}