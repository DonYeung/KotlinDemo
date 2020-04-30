package com.example.app.broadcastdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReciver2 : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
         Toast.makeText(context,"MyBroadcastReciver2 sandak", Toast.LENGTH_SHORT).show()
        abortBroadcast()
    }
}