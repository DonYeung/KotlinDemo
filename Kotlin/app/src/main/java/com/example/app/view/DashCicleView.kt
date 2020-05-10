package com.example.app.view

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.app.px
import kotlin.math.cos
import kotlin.math.sin

/**
 * 绘制圆弧表盘
 */
//曲线长度
val LENGTH = 120f.px
private val RADIUS = 150f.px
val OPEN_ANGLE = 120f
val DASH_WIDTH = 2f.px
var DASH_LENGTH = 10f.px


class DashCicleView constructor(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    companion object {
        const val TAG = "Don"
    }

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    val dashPath = Path()
    val path = Path()
    lateinit var pathEffect :PathEffect
    lateinit var pathMeasure:PathMeasure
    var arrayNum = listOf("0","20","40","60","80","100","120","140","160","180","200")


    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3f.px
        paint.color = Color.BLUE
        textPaint.style = Paint.Style.FILL
        textPaint.color = Color.BLACK
        textPaint.textSize =30f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.addArc(width / 2f -RADIUS,
                height / 2f -RADIUS,width/2f +RADIUS,height/2f+ RADIUS,90+OPEN_ANGLE/2F,
                360-OPEN_ANGLE)

        pathMeasure = PathMeasure(path,false)

        for(i in 0..20){
            if (i%2 == 0 ){
                DASH_LENGTH = 15f.px
            }else{
                DASH_LENGTH = 10f.px
            }
        }

        dashPath.addRect(0f,0f,DASH_WIDTH, DASH_LENGTH,Path.Direction.CCW )

        pathEffect = PathDashPathEffect(dashPath,(pathMeasure.length - DASH_WIDTH)/20f,0f,PathDashPathEffect.Style.ROTATE)
        Log.i(TAG, "onSizeChanged: ${((pathMeasure.length - DASH_WIDTH)/20f).toInt()}")
    }

    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//        canvas.save()
        //画圆弧
        canvas.drawPath(path, paint)
        //画刻度
        paint.pathEffect = pathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null
//        canvas.restore()


        canvas.drawLine(width/2f,height/2f, width/2 +  (LENGTH* cos(markAngle(10 ))).toFloat(),
                height/2f +(LENGTH*sin(markAngle(10))).toFloat(),paint)
/*
        for (i in 0..20 step 2) {
            if (i<=10){
                textPaint.textAlign = Paint.Align.LEFT
            }else{
                textPaint.textAlign = Paint.Align.CENTER
            }
            canvas.drawText("${i*10}",
                    width/2f + RADIUS*cos(markAngle(i).toFloat()),
                    height/2f + RADIUS*sin(markAngle(i).toFloat())+15f.px,textPaint)
        }*/
        for (i in 0..20 step 2){
            var point  = getCoordinatePoint(RADIUS+10f.px,90+ OPEN_ANGLE/2f + (360- OPEN_ANGLE)/20f*i)

            canvas.drawText("${i*10}",point[0],point[1],textPaint)
        }
    }

    private fun markAngle(value :Int) = Math.toRadians((90+ OPEN_ANGLE/2f + (360- OPEN_ANGLE)/20f*value).toDouble())

    private fun getCoordinatePoint(radius: Float, cirAngle: Float): FloatArray {
        val point = FloatArray(2)
        var arcAngle = Math.toRadians(cirAngle.toDouble()) //将角度转换为弧度
        if (cirAngle < 90) {
            point[0] = (width/2f + Math.cos(arcAngle) * radius).toFloat()
            point[1] = (height/2f + Math.sin(arcAngle) * radius) .toFloat()
            Log.i(TAG, "getCoordinatePoint: 1")
        } else if (cirAngle == 90f) {
            point[0] = width/2f
            point[1] = height/2f + radius
            Log.i(TAG, "getCoordinatePoint: 2")

        } else if (cirAngle > 90 && cirAngle < 180) {
            arcAngle = Math.PI * (180 - cirAngle) / 180.0
            point[0] = (width/2f -5f.px - Math.cos(arcAngle) * radius).toFloat()
            point[1] = (height/2f + Math.sin(arcAngle) * radius).toFloat()
            Log.i(TAG, "getCoordinatePoint: 3")

        } else if (cirAngle == 180f) {
            point[0] = width/2f - radius
            point[1] = height/2f
            Log.i(TAG, "getCoordinatePoint: 4")

        } else if (cirAngle > 180 && cirAngle < 270) {
            arcAngle = Math.PI * (cirAngle - 180) / 180.0
            point[0] = (width/2f - 5f.px - Math.cos(arcAngle) * radius).toFloat()
            point[1] = (height/2f - Math.sin(arcAngle) * radius).toFloat()
            Log.i(TAG, "getCoordinatePoint: 5")

        } else if (cirAngle == 270f) {
            point[0] = width/2f -5f.px
            point[1] = height/2f - radius
            Log.i(TAG, "getCoordinatePoint: 6")

        } else {
            arcAngle = Math.PI * (360 - cirAngle) / 180.0
            point[0] = (width/2f + Math.cos(arcAngle) * radius).toFloat()
            point[1] = (height/2f - Math.sin(arcAngle) * radius).toFloat()
            Log.i(TAG, "getCoordinatePoint: 7")

        }
        return point
    }

}