package com.example.app.view

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.example.app.R
import kotlinx.android.synthetic.main.layout_title.view.*

class TitleLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.layout_title,this)

        btn_back.setOnClickListener { 
            val activity = context as Activity
            activity.finish()

        }

        btn_go.setOnClickListener {
            Toast.makeText(context,"sadsadsadsa", Toast.LENGTH_SHORT).show()
        }
    }
}