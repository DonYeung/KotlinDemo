package com.example.app.notificationmanagerdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_CANCEL_CURRENT
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.app.FirstActivity
import com.example.app.R

class NotifityDemo :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun createNotifity(){
        val pengdingIntent = Intent(this,FirstActivity::class.java)
        val pi = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
            val channel = NotificationChannel("nomal","Normal",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val notifiticaiton = NotificationCompat.Builder(this,"normal")
                .setContentTitle("content title")
                .setContentText("hahahhaa")
                .setContentIntent(pi)
                .setAutoCancel(false) // 点击后不消失 true为点击后消失
                .setSmallIcon(R.drawable.iv_avatar)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.ic_launcher_background))
                .build()
        manager.notify(1,notifiticaiton)
    }
}