package com.example.app.servicedemo

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    companion object{
        private const val TAG = "MyService"
    }

    val binder = DownloadBinder()

    class DownloadBinder :Binder(){
        fun startDown(){

        }
        fun getDownProgress():Int{
            return 100
        }
    }
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}
