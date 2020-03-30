package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.Utils
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken
import okhttp3.internal.Util

class LessonPresenter {
    private val LESSON_PATH = "lessons"
    lateinit var activity: LessonActivity

    constructor(activity: LessonActivity) {
        this.activity = activity
    }

    private var lessons = ArrayList<Lesson>()

    private val type = object : TypeToken<ArrayList<Lesson>>() {}.type

//    private val type = object : TypeToken<List<Lesson?>?>() {}.type

    fun fetchData() {
        HttpClient.get(LESSON_PATH, type, object : EntityCallback<ArrayList<Lesson>> {
            override fun onSuccess(entity: ArrayList<Lesson>) {
                this@LessonPresenter.lessons = entity
                activity.runOnUiThread {
                    activity.showResult(lessons)
                }
            }

            override fun onFailure(message: String) {
                activity.runOnUiThread {
                    Utils.toast(message)
                }
            }

        })
    }

    fun showPlayback() {
        var playbackLessons = ArrayList<Lesson>()
        /*for (lesson in lessons){
            if (lesson.state == Lesson.State.PLAYBACK) {
                playbackLessons.add(lesson);
            }
        }
        activity.showResult(playbackLessons)*/



        /*lessons.forEach() {
            if (it.state == Lesson.State.PLAYBACK) {
                playbackLessons.add(it);
            }

        }
        activity.showResult(playbackLessons)
        */

        /*
        val filter = lessons.filter {
            it.state == Lesson.State.PLAYBACK
        }*/


        activity.showResult(lessons.filter {
            it.state == Lesson.State.PLAYBACK
        })


    }


}