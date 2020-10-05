package com.example.core

import android.app.Application
import android.content.Context
import com.example.core.utils.DPHolder

class BaseApplication :Application(){

    companion object{
        @get:JvmName("currentApplication")
        lateinit var currentApplication : Context

        /*fun currentApplication():Context{
            return currentApplication
        }*/
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        currentApplication = this
    }
    override fun onCreate() {
        super.onCreate()
        initDsdk()
    }

    fun initDsdk(){
        DPHolder.getInstance().init(currentApplication)
    }
}