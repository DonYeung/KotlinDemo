package com.example.app.circleview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.app.px

private val RADIUS = 100f.px
private val PADDING = 100f.px

class MyCircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(PADDING+ RADIUS, PADDING+ RADIUS, RADIUS,paint)
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val size = ((RADIUS+ PADDING)*2).toInt()
        val width = resolveSize(size,widthMeasureSpec)
        val height = resolveSize(size,heightMeasureSpec)
        setMeasuredDimension(width,height)

    }
}