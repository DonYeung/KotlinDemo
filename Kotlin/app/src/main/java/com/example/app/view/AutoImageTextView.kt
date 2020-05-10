package com.example.app.view

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.app.R
import com.example.app.px
import com.example.core.utils.dp2px

private val IMAGE_PADDING = 50F.px
private val IMAGE_WIDTH = 150f.px

class AutoImageTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 15f.px
        textAlign = Paint.Align.LEFT
    }
    val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer a mauris sit amet quam ultricies porta id sed elit. Nullam ligula ligula, congue sed lectus vel, interdum dignissim enim. Sed eu pharetra orci. Vivamus sit amet rhoncus mauris. Cras sit amet eleifend justo, sed aliquam nunc. Sed lectus tellus, hendrerit gravida tellus sed, viverra rutrum est. Sed finibus dapibus justo, blandit laoreet turpis. Donec porttitor at mauris id finibus. Duis viverra sem vel nunc viverra ornare et id nibh. Quisque eleifend, tellus sit amet eleifend consectetur, magna sapien elementum ex, ac sollicitudin augue ipsum sit amet leo. Sed id massa ultricies justo mattis mattis vitae vitae sem.\n" +
            "\n"

    val fontMetrics = Paint.FontMetrics()

    val bitmap = getImage(IMAGE_WIDTH.toInt())

    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
        canvas.drawBitmap(bitmap,width- IMAGE_WIDTH, IMAGE_PADDING,paint)
        paint.getFontMetrics(fontMetrics)
        var measuredWidth = floatArrayOf(0f)
        /*var count = paint.breakText(text,true,width.toFloat(),measuredWidth)
        canvas.drawText(text,0,count,0f,0-fontMetrics.top,paint)
        var oldcount = count
        count = paint.breakText(text,oldcount,text.length,true,width.toFloat(),measuredWidth)
        canvas.drawText(text,oldcount,oldcount+count,0f,-fontMetrics.top+paint.fontSpacing,paint)*/

        var start = 0
        var count = 0
        var offsetY = -fontMetrics.top
        var maxwidth = 0f
        while (start< text.length){
            if (offsetY< IMAGE_PADDING ||offsetY > IMAGE_PADDING+ IMAGE_WIDTH){
                maxwidth = width.toFloat()
            }else{
                maxwidth =  width.toFloat() - IMAGE_WIDTH
            }
            count = paint.breakText(text,start,text.length,true,maxwidth,measuredWidth)
            canvas.drawText(text,start,start+count,0f, offsetY,paint)
            start += count
            offsetY += paint.fontSpacing
        }
    }



    private fun getImage(width: Int): Bitmap {
        val option = BitmapFactory.Options()
        option.inJustDecodeBounds = true
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_avatar2, option)
        option.inJustDecodeBounds = false
        option.inDensity = option.outWidth
        option.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.ic_avatar2, option)
    }

}