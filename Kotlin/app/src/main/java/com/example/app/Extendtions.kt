package com.example.app

import android.content.ContentValues
import android.content.SharedPreferences
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue

val Float.px
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this, Resources.getSystem().displayMetrics)
val Int.px
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this.toFloat(),Resources.getSystem().displayMetrics)

fun SharedPreferences.open(block:(SharedPreferences.Editor.() -> Unit)){
    val editer = edit()
    editer.block()
    editer.apply()
}

fun cvOf(vararg pairs:Pair<String,Any?>):ContentValues{
    val cv = ContentValues()
    for(pair in pairs){
        val key = pair.first
        val value = pair.second
        when(value){
            is Int -> cv.put(key,value)
            is Short -> cv.put(key,value)
            is Float -> cv.put(key,value)
            is Double -> cv.put(key,value)
            is String -> cv.put(key,value)
            is Byte -> cv.put(key,value)
            is ByteArray -> cv.put(key,value)
            null -> cv.putNull(key)
        }

    }
    return cv
}