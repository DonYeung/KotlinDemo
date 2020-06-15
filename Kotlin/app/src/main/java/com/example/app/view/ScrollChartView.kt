package com.example.app.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.example.app.Temperature
import com.example.app.dp


class ScrollChartView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private  var list = mutableListOf<Temperature>()
    private  var temperatureList = mutableListOf<Double>()

    //每个item的宽度
    private val itemWidth = 0
    //温度基准高度
    private val lowestTempHeight = 0
    //温度基准高度
    private val highestTempHeight = 0

    //View宽高
    private val mWidth = 0
    private val mHeight = 0
    //最低温
    private var lowestTemp :Double? =null
    //最高温
    private var highestTemp:Double? =null
    //默认高
    private var defHeightPixel = 0
    private var defWidthPixel = 0

    private val path = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.YELLOW
        strokeWidth = 13.dp
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawLine(canvas)
        drawBitmap(canvas)
        drawText(canvas)
    }

    fun setData(list: List<Temperature>){
        this.list = list as MutableList<Temperature>
        for ((index,value) in  list.withIndex()){

            temperatureList.add(index,value.value)
        }
        lowestTemp = temperatureList.min()
        highestTemp = temperatureList.max()
        invalidate()

    }
    private fun drawLine(canvas: Canvas){
        for ((index,value) in  temperatureList.withIndex()){
            val w = (itemWidth*index + 20 ).toFloat()
            val h  = tempHeightPixel(value) +20
            if (index==0){
                path.moveTo(w,h)
            }
            else{
                path.lineTo(w,h)
            }
        }
        canvas.drawPath(path,paint)

    }
    private fun drawBitmap(canvas: Canvas) {

    }
    private fun drawText(canvas: Canvas) {
        canvas.drawText("shshsh",width/2f,height/2f,paint)
    }

    fun tempHeightPixel(tmp: Double): Float {
        val res = ((tmp - lowestTemp!!) / (highestTemp!! - lowestTemp!!) * (highestTempHeight - lowestTempHeight) + lowestTempHeight.toFloat()).toFloat()
        return defHeightPixel - res //y从上到下
    }


}