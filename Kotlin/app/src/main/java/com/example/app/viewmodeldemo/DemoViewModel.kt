package com.example.app.viewmodeldemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class DemoViewModel(countReserved:Int):ViewModel() {
    var counter =countReserved

    val data = liveData {
        kotlin.runCatching {
            emit("haha")
        }
    }

}