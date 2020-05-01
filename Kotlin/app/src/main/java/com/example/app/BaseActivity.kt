package com.example.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity :AppCompatActivity() {
    companion object{
        private const val TAG = "BaseActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ${javaClass.name}")
        ActivityConllector.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityConllector.removeActivity(this)
    }


}