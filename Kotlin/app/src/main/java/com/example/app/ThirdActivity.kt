package com.example.app

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class ThirdActivity : AppCompatActivity() {
    companion object {
        fun actionStart(context: Context, data1: String, data2: String) {
            /* val intent = Intent()
             intent.setClass(context,ThirdActivity::class.java)
             intent.putExtra("param1",data1)
             intent.putExtra("param2",data2)
             context.startActivity(intent)*/
            //简化
            val intent = Intent(context, ThirdActivity::class.java).apply {
                putExtra("param1", data1)
                putExtra("param2", data2)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data_return", "hhelp")
            setResult(Activity.RESULT_OK, intent)
            finish()

        }


    }

    fun test() {
        val handlerThread = HandlerThread("haha")
        handlerThread.start()
        val handler = object : Handler(handlerThread.looper) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
            }
        }

        val thread = object : Thread() {
            override fun run() {
                val message = Message.obtain()
                message.what = 0
                message.arg1 = 1
                handler.sendMessage(message)
            }
        }
    }


}