package com.example.app.servicedemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import com.example.app.startActivity
import kotlinx.android.synthetic.main.activity_textcenter.*

class ServiceDemoActivity:AppCompatActivity() {
    private lateinit var mBinder:MyService.DownloadBinder

    private val mServiceCon = object:ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mBinder = service as MyService.DownloadBinder
            mBinder.startDown()
            mBinder.getDownProgress()
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_textcenter)
        btn1.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
//            startService(intent)
            bindService(intent,mServiceCon, Context.BIND_AUTO_CREATE)
        }

        //intentservice
        btn1.setOnClickListener {
//            val intent = Intent(this,MyIntentService::class.java)
//            startService(intent)

            //高级进阶reified
            startActivity<MyIntentService>(this)
            //高级进阶reified
            startActivity<MyIntentService>(this){
                putExtra("haha",123)
                putExtra("hehe","asdsad")
            }
        }

    }
}