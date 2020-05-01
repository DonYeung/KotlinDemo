package com.example.app

import android.app.Activity

object ActivityConllector {
    private val activities = arrayListOf<Activity>()

    fun addActivity(activity: Activity){
        activities.add(activity)
    }
    fun removeActivity(activity: Activity){
        activities.remove(activity)
    }
    fun finishAll(){
        for (activity in activities){
            if (!activity.isFinishing){
                activity.finish()
            }
        }
        activities.clear()
    }
}