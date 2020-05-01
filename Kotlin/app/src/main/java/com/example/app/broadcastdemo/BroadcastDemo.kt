package com.example.app.broadcastdemo

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.app.R
import kotlinx.android.synthetic.main.activity_constaint2.*

class BroadcastDemo :AppCompatActivity() {
    private lateinit var myBroadcastReciver :MyBroadcastReciver
    private lateinit var localBroadcastManager: LocalBroadcastManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constaint2)

        myBroadcastReciver = MyBroadcastReciver()
        val myBroadcastReciver2 = MyBroadcastReciver2()
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.shuixin.customAction")
        registerReceiver(myBroadcastReciver,intentFilter)

        localBroadcastManager = LocalBroadcastManager.getInstance(this)
//        val intentFilter = IntentFilter()
        intentFilter.addAction("com.shuixin.customAction")
        localBroadcastManager.registerReceiver(myBroadcastReciver,intentFilter)
        btn1.setOnClickListener {
            val intent = Intent()
            intent.action = "com.shuixin.customAction"
//            sendBroadcast(intent)
//            sendOrderedBroadcast(intent,null)
            localBroadcastManager.sendBroadcast(intent)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myBroadcastReciver)
    }
}