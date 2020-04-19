package com.example.app.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.example.app.px

class PointAnimatorView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
        style = Paint.Style.FILL
        strokeWidth = 50f.px
        strokeCap = Paint.Cap.ROUND
    }
    var pointF = PointF(50f.px,50f.px)
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
        canvas.drawPoint(pointF.x,pointF.y,paint)
    }
}