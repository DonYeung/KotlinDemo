package com.example.app.delegatedemo

import android.content.UriMatcher
import android.net.Uri
import kotlin.reflect.KProperty

class Later<T>(val block:()->T) {
    var value :Any? = null
    operator fun getValue(any:Any?,prop:KProperty<*>):T{
        if (value==null){
            value = block
        }
        return value as T
    }
}

fun <T> later(block: () -> T) = Later(block)

fun main(){
    val matcher by later {
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        val authority = "content://com.example.hencoder.provicer"
        uriMatcher.addURI(authority,"book",1)
        uriMatcher
    }
}