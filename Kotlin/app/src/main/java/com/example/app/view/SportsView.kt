package com.example.app.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.example.app.R
import com.example.app.utils.px
import android.view.View.TEXT_ALIGNMENT_CENTER as TEXT_ALIGNMENT_CENTER1

private val RAUDIUS =100F.px
class SportsView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 20f.px
    }
    private val fontMetrics = Paint.FontMetrics()
    private val bounds = Rect()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
        paint.color = Color.parseColor("#ABABAB")
        canvas.drawCircle(width/2f,height/2f,RAUDIUS,paint)
        paint.strokeCap = Paint.Cap.ROUND
        paint.color = Color.parseColor("#FFB1B0")
        canvas.drawArc(width/2f- RAUDIUS,height/2f- RAUDIUS,width/2f+ RAUDIUS,height/2f+ RAUDIUS,270f,200f,false,paint)



        //绘制文字
        paint.typeface = ResourcesCompat.getFont(context, R.font.dinbold)
        paint.textAlign = Paint.Align.CENTER
        paint.textSize = 40f.px
        paint.style = Paint.Style.FILL
//        paint.getFontMetrics(fontMetrics)
//        canvas.drawText("adad",width/2f,height/2f-(fontMetrics.top-fontMetrics.bottom)/2,paint)

        paint.getTextBounds("adad",0,"adad".length,bounds)
        canvas.drawText("adad",width/2f,height/2f-(bounds.top-bounds.bottom)/2,paint)

    }
}