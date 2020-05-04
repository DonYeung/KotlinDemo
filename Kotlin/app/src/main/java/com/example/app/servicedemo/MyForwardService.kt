package com.example.app.servicedemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.app.FirstActivity

class MyForwardService : Service() {
    override fun onCreate() {
        super.onCreate()
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("MyForwardService", "前台服务通知", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this,FirstActivity::class.java)
        val pi = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT)


        val notification = NotificationCompat.Builder(this,"MyForwardService")
                .setContentText("hahahha")
                .setContentTitle("TITLE")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build()
        startForeground(1,notification)

    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
