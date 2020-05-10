package com.example.app.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.app.px
import kotlin.math.cos
import kotlin.math.sin

private val RADIUS = 100f.px
private val OFFSET = 10f.px
class PieView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val paint =  Paint(Paint.ANTI_ALIAS_FLAG)
    val COLORRES = intArrayOf(Color.BLUE,Color.GREEN,Color.GRAY,Color.RED,Color.YELLOW)
    val list = floatArrayOf(60f,90f,30f,130f,50f)
    init {
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
        canvas.save()
        var startAngle = 0f
        for((index,angle) in list.withIndex()){
            paint.color = COLORRES[index]
            if (index == 3 )
                canvas.translate(cos(markAngle(startAngle+angle/2)).toFloat() * OFFSET ,
                        sin(markAngle(startAngle+angle/2)).toFloat() * OFFSET )

            canvas.drawArc(width/2f-RADIUS,height/2f-RADIUS,width/2f+RADIUS,height/2f+RADIUS,
                    startAngle,angle,true,paint)
            startAngle += list[index]
            if (index == 3)
                canvas.restore()
        }


    }

    private fun markAngle(angle: Float) = Math.toRadians((angle).toDouble())
}