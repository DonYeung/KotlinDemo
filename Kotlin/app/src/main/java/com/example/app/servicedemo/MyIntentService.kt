package com.example.app.servicedemo

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService(name: String?) : IntentService(name) {
    companion object{
        private const val TAG= "Don"
    }
    override fun onHandleIntent(intent: Intent?) {
        Log.i(TAG, "onHandleIntent: ${Thread.currentThread().name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }
}