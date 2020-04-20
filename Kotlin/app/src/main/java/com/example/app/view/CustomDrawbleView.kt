package com.example.app.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.example.app.px

class CustomDrawbleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val drawble = CustomDrawble()
    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
        drawble.setBounds(50f.px.toInt(),80f.px.toInt(),width  ,height)
        drawble.draw(canvas)
//        canvas.drawBitmap()
    }
}