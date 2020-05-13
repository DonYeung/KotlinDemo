package com.example.app.workmanagerdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.work.*
import com.example.app.R
import java.util.concurrent.TimeUnit

class WorkerDemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)


        val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()


        //周期性任务(不能短于15分钟)
        val requst2 = PeriodicWorkRequest.Builder(SimpleWorker::class.java, 15, TimeUnit.MINUTES).build()

        WorkManager.getInstance(this).enqueue(request)


        //5分钟后执行
        val request3 = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
                .setInitialDelay(5, TimeUnit.MINUTES)
                .build()

        //添加tag，以tag来取消后台任务请求
        val request4 = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
                .addTag("simple")
                .build()

        WorkManager.getInstance(this).cancelAllWorkByTag("simple")
        //or
        WorkManager.getInstance(this).cancelWorkById(request4.id)


        //监听
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request4.id)
                .observe(this) { workInfo ->
                    if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                        Log.i("Don", "onCreate: SUCCEEDED")
                    } else if(workInfo.state == WorkInfo.State.FAILED){
                        Log.i("Don", "onCreate: faile")

                    }
                }





    }
}