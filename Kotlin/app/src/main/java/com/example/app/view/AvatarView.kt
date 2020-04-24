package com.example.app.view

import android.content.Context
import android.graphics.*
import android.graphics.Shader.TileMode
import android.util.AttributeSet
import android.view.View
import com.example.app.R
import com.example.app.utils.px


private val IMAGE_WIDTHG = 200f.px
private val IMAGE_PADDING = 100f.px
private val OFFSET = 10F.px
val XFERMODE = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)
class AvatarView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bounds = RectF(IMAGE_PADDING- OFFSET,IMAGE_PADDING- OFFSET,
            IMAGE_PADDING+ IMAGE_WIDTHG+ OFFSET,IMAGE_PADDING+ IMAGE_WIDTHG+ OFFSET)
    private val ovalRectF1 = RectF(IMAGE_PADDING- OFFSET,IMAGE_PADDING- OFFSET,
            IMAGE_PADDING+ IMAGE_WIDTHG+ OFFSET,IMAGE_PADDING+ IMAGE_WIDTHG+ OFFSET)
    private val ovalRectF2 = RectF(IMAGE_PADDING,IMAGE_PADDING,
            IMAGE_PADDING+ IMAGE_WIDTHG,IMAGE_PADDING+ IMAGE_WIDTHG)
    val bitmap = getAvatar(IMAGE_WIDTHG.toInt())

    init{
    }
    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
        canvas.drawOval(ovalRectF1,paint)
        val count = canvas.saveLayer(bounds,null)
        canvas.drawOval(ovalRectF2,paint)

        paint.xfermode = XFERMODE
//
        canvas.drawBitmap(getAvatar(IMAGE_WIDTHG.toInt()),IMAGE_PADDING,IMAGE_PADDING,paint)
        paint.xfermode = null
        canvas.restoreToCount(count)


    }

    //输出图片的宽高= 原图片的宽高 / inSampleSize * (inTargetDensity / inDensity)
    private fun getAvatar(width :Int) :Bitmap{
        val option = BitmapFactory.Options()
        option.inJustDecodeBounds=true //不加载到内存
        BitmapFactory.decodeResource(this.resources, R.drawable.ic_avatar2,option)
        option.inJustDecodeBounds=false //不加载到内存
        option.inDensity = option.outWidth
        option.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.ic_avatar2,option)
    }
}