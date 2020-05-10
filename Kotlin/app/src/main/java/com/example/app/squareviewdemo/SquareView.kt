package com.example.app.squareviewdemo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.example.app.R
import com.example.app.dp
import kotlin.math.min

class SquareView(context: Context?, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {

    private val bitmap = getAvatar(200.dp.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

   /* override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap,0f,0f,paint)
    }*/
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        val w = MeasureSpec.getSize(widthMeasureSpec)
//        val h = MeasureSpec.getSize()
        var size  = min(measuredWidth,measuredHeight)
//        width = resolveSize(width,widthMeasureSpec)
//        val height = resolveSize(width,heightMeasureSpec)
        setMeasuredDimension(size,size)

    }

    fun getAvatar(width :Int):Bitmap{
        val option = BitmapFactory.Options()
        option.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.iv_avatar,option)
        option.inJustDecodeBounds = false
        option.inDensity = option.outWidth
        option.inTargetDensity = width
        return BitmapFactory.decodeResource(resources,R.drawable.iv_avatar,option)
    }
}