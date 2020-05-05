package com.example.app.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.app.dp
import com.example.app.getAvatar

/**
 * 多点触摸--协作型
 */
class MultiTouchView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(resources, 200.dp.toInt())
    private var orinalOffsetX = 0f
    private var orinalOffsetY = 0f
    private var offsetX = 0f
    private var offsetY = 0f
    private var downX = 0f
    private var downY = 0f
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var focusX: Float?
        var focusY: Float?
        var sumX = 0f
        var sumY = 0f
        val isPointerUp = event.actionMasked == MotionEvent.ACTION_POINTER_UP
        for (i in 0 until event.pointerCount) {
            if (!(isPointerUp && i == event.actionIndex)) {
                sumX += event.getX(i)
                sumY += event.getY(i)
            }
        }
        var count = event.pointerCount
        if (isPointerUp) {
            count--
        }
        focusX = sumX / count
        focusY = sumY / count
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_POINTER_UP -> {
                downX = focusX
                downY = focusY
                orinalOffsetX = offsetX
                orinalOffsetY = offsetY
            }
            MotionEvent.ACTION_MOVE -> {
                offsetX = focusX - downX + orinalOffsetX
                offsetY = focusY - downY + orinalOffsetY
                invalidate()
            }
        }

        return true
    }
}