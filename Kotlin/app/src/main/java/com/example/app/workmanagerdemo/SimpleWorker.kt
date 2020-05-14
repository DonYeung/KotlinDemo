package com.example.app.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class SimpleWorker(context: Context,params:WorkerParameters): Worker(context,params) {
    override fun doWork(): Result {
        //子线程
        Log.i("Don", "doWork: ${Thread.currentThread().name}")
        return Result.success()

    }
}