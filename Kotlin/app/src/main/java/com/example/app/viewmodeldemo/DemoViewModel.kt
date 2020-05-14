package com.example.app.viewmodeldemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DemoViewModel(countReserved:Int):ViewModel() {
    var counter =countReserved

}