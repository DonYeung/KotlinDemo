package com.example.app.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.app.R
import com.example.app.px

class AvatarAnimatorView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val IMAGE_WIDTH = 200F.px
    private val IMAGE_PADDING = 100F.px

    private val bitmap = getImage(IMAGE_WIDTH.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val camera = Camera().apply {
        setLocation(0f,0f,-6f * resources.displayMetrics.density)
    }

    private var bottomCRotation = 0f
        set(value) {
            field=value
            invalidate()
        }
    private var topCRotation = 0f
        set(value) {
            field=value
            invalidate()
        }
    private var canvasRotation = 0f
        set(value) {
            field=value
            invalidate()
        }
    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
        //上半部分
        canvas.save()
        canvas.translate((IMAGE_PADDING+ IMAGE_WIDTH/2),(IMAGE_PADDING+ IMAGE_WIDTH/2))
        canvas.rotate(-canvasRotation)

        camera.save()
        camera.rotateX(topCRotation)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(-(IMAGE_PADDING+ IMAGE_WIDTH),-(IMAGE_PADDING+ IMAGE_WIDTH),IMAGE_WIDTH,0f)
        canvas.rotate(canvasRotation)
        canvas.translate(-(IMAGE_PADDING+ IMAGE_WIDTH/2),-(IMAGE_PADDING+ IMAGE_WIDTH/2))
        canvas.drawBitmap(bitmap,IMAGE_PADDING,IMAGE_PADDING,paint)
        canvas.restore()

        //下半部分
        canvas.translate((IMAGE_PADDING+ IMAGE_WIDTH/2),(IMAGE_PADDING+ IMAGE_WIDTH/2))
        canvas.rotate(-canvasRotation)
        camera.save()
        camera.rotateX(bottomCRotation)
        camera.applyToCanvas(canvas)
        camera.restore()
//        canvas.rotate(30f)
        canvas.clipRect(-(IMAGE_PADDING+ IMAGE_WIDTH),0f,IMAGE_WIDTH,IMAGE_WIDTH)
        canvas.rotate(canvasRotation)
        canvas.translate(-(IMAGE_PADDING+ IMAGE_WIDTH/2),-(IMAGE_PADDING+ IMAGE_WIDTH/2))
        canvas.drawBitmap(bitmap,IMAGE_PADDING,IMAGE_PADDING,paint)
    }


    private fun getImage(width:Int):Bitmap{
        val option = BitmapFactory.Options()
        option.inJustDecodeBounds= true
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.iv_avatar,option)
        option.inJustDecodeBounds=false
        option.inDensity = option.outWidth
        option.inTargetDensity = width
        return BitmapFactory.decodeResource(resources,R.drawable.iv_avatar,option)
    }
}