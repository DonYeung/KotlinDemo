package com.example.app.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.app.px

class CircleAnimatorView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var RADIUS=50F.px
        set(value) {
            field = value
            invalidate()
        }
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }
    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
        canvas.drawCircle(width/2f,height/2f,RADIUS,paint)
    }
}