package com.example.app.view

import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.graphics.toColorInt
import com.example.app.px

class CustomDrawble :Drawable() {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
//        color = "#F9A825".toColorInt()
        strokeWidth = 5f.px
    }
    private val drawable = ColorDrawable()
    private val INTERVAL =50F.px

    var x = bounds.left.toFloat()
    var y = bounds.top.toFloat()
    override fun draw(canvas: Canvas) {
//        drawable.setBounds()

        while (x<= bounds.right) {
            canvas.drawLine(x, bounds.top.toFloat(), x, bounds.bottom.toFloat(),paint)
            x += INTERVAL.toInt()
        }
        while (y <= bounds.right){
            canvas.drawLine(bounds.left.toFloat(),y, bounds.right.toFloat(),y,paint)
            y += INTERVAL
        }

        drawable.draw(canvas)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }
    fun getAlapha() : Int{
        return paint.alpha
    }

    override fun getOpacity(): Int {
        return  when(alpha){
            0 -> PixelFormat.TRANSPARENT
            0xff -> PixelFormat.OPAQUE
            else -> PixelFormat.TRANSLUCENT
        }
//        return
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter  = colorFilter
    }
    override fun getColorFilter():ColorFilter{
        return paint.colorFilter
    }
}