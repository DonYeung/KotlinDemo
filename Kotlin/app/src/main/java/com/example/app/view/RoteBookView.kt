package com.example.app.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.app.R
import com.example.app.utils.px

private val IMAGE_WIDTH = 200f.px
private val IMAGE_PADDING = 100F.px
class RoteBookView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val bitmap = getImage(IMAGE_WIDTH.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val camera = Camera().apply {
        setLocation(0f,0f,-6 * resources.displayMetrics.density)
    }
    init {
        camera.rotateX(45f)
    }

    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)

        canvas.save()
        //上半部分
        canvas.translate((IMAGE_PADDING+ IMAGE_WIDTH/2),(IMAGE_PADDING+ IMAGE_WIDTH/2))
        canvas.rotate(-30f)
        canvas.clipRect(-(IMAGE_PADDING+ IMAGE_WIDTH),- IMAGE_WIDTH, IMAGE_WIDTH,0f)
        canvas.rotate(30f)
        canvas.translate(-(IMAGE_PADDING+ IMAGE_WIDTH/2),-(IMAGE_PADDING+ IMAGE_WIDTH/2))
        canvas.drawBitmap(bitmap, IMAGE_PADDING, IMAGE_PADDING,paint)
        canvas.restore()

        //下半部分
        canvas.translate((IMAGE_PADDING+ IMAGE_WIDTH/2),(IMAGE_PADDING+ IMAGE_WIDTH/2))
        canvas.rotate(-30f)
        camera.applyToCanvas(canvas)
        canvas.clipRect(-(IMAGE_PADDING+ IMAGE_WIDTH),0f, IMAGE_WIDTH,IMAGE_WIDTH)
        canvas.rotate(30f)
        canvas.translate(-(IMAGE_PADDING+ IMAGE_WIDTH/2),-(IMAGE_PADDING+ IMAGE_WIDTH/2))
        canvas.drawBitmap(bitmap, IMAGE_PADDING, IMAGE_PADDING,paint)
    }

    private fun getImage(width: Int):Bitmap{
        val option  = BitmapFactory.Options()
        option.inJustDecodeBounds = true
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_avatar2,option)
        option.inJustDecodeBounds = false
        option.inDensity = option.outWidth
        option.inTargetDensity = width
        return BitmapFactory.decodeResource(resources,R.drawable.ic_avatar2,option)
    }
}