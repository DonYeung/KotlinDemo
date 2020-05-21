package com.example.app.dragdemo

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.app.R
import kotlinx.android.synthetic.main.activity_draghelper_view.*

class Draghelperdemo:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draghelper_view)
        var topParentView = view1.parent
        var index = 0
        while (topParentView.parent!=null){
            topParentView = topParentView.parent
            index+=1
            println("view:${topParentView}")
        }
        println("index:${index}")
    }
}