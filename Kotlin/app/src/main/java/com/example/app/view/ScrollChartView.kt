package com.example.app.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.widget.OverScroller
import com.example.app.Temperature
import com.example.app.dp
import com.example.core.utils.toMonthDate
import kotlin.math.abs
import kotlin.math.max


class ScrollChartView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private  var mList = mutableListOf<Temperature>()
    private  var temperatureList = mutableListOf<Double>()
    private  var xAiasList = mutableListOf<String>()

    //每个item的宽度
    private var itemWidth = 0
    //温度基准高度
    private var lowestTempHeight = 0
    //温度基准高度
    private var highestTempHeight = 0

    private var mWidth = 0
    private var mHeight = 0

    //最低温
    private var lowestTemp =0f
    //最高温
    private var highestTemp = 0f
    //默认高
    private var defHeightPixel = 0f
    private var defWidthPixel = 0

    private var paddingL =0
    private var paddingT = 0
    private var paddingR = 0
    private var paddingB = 0

    private val mPoints = mutableListOf<Point>()

    private val viewConfig = ViewConfiguration.get(context)
    private val touchSlop = viewConfig.scaledPagingTouchSlop
    private val velocityTracker = VelocityTracker.obtain()
    private val minVelocity = viewConfig.scaledMinimumFlingVelocity
    private val maxVelocity = viewConfig.scaledMaximumFlingVelocity


    private var downX = 0f
    private var downY = 0f
    private var downScrollX = 0
    private var isScrolling = false
    private val overScroller = OverScroller(context)

    private val fontMetrics = Paint.FontMetrics()

    private val path = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.BLUE
        strokeWidth = 2.dp
    }
    private val dashPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.GRAY
        strokeWidth = 1.dp
    }
    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.BLUE
        strokeWidth = 5.dp
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.BLACK
        strokeWidth = 2.dp
        textSize = 13.dp
    }
    init {
        lowestTempHeight = 100.dp.toInt()//长度  非y轴值
        highestTempHeight = 140.dp.toInt();
        defHeightPixel = 150.dp

        paddingL = 15.dp.toInt()
        paddingB = 15.dp.toInt()
        paddingT = 15.dp.toInt()
        paddingR = 15.dp.toInt()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        paddingL = max(paddingL,paddingLeft)
        paddingR = max(paddingR,paddingRight)
        paddingT = max(paddingT,paddingTop)
        paddingB = max(paddingB,paddingBottom)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        //获取测量大小
        //获取测量大小
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)


        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize + paddingL + paddingR
            mHeight = heightSize
        }

        //如果为wrap_content 那么View大小为默认值
        //如果为wrap_content 那么View大小为默认值
        if (widthMode == MeasureSpec.UNSPECIFIED && heightMode == MeasureSpec.AT_MOST) {
            mWidth = defWidthPixel + paddingL + paddingR
            mHeight = (defHeightPixel + paddingT + paddingB).toInt()
        }

        //设置视图的大小
        //设置视图的大小
        setMeasuredDimension(mWidth, mHeight)
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawDash(canvas)
        drawLine(canvas)
        drawBitmap(canvas)
        drawText(canvas)
        drawCircle(canvas)
    }


    fun setData(list: List<Temperature>){
        this.mList = list as MutableList<Temperature>
        for ((index,value) in  list.withIndex()){
            xAiasList.add(index,value.datetime)
            temperatureList.add(index,value.value)
        }
        lowestTemp = temperatureList.min()!!.toFloat()
        highestTemp = temperatureList.max()!!.toFloat()
        initPoint()
        invalidate()

    }
    private fun initPoint() {
        val interval = paddingL
        val itemHeight = height/(highestTemp- lowestTemp)
        itemWidth = width/7


        for ((index, value) in mList.withIndex()) {
//            val x = interval*index + interval
            var xx = itemWidth * index.toFloat() + interval + paddingL
            val y = tempHeightPixel(value.value) +paddingT
            Log.i("Don", "initPoint: ${xx},${y}")
             val point = Point()
            point.x = xx.toInt()
            point.y = y.toInt()
            mPoints.add(point)
            xx += interval
        }
    }
    private fun drawCircle(canvas: Canvas){
        for ((index,value) in  mPoints.withIndex()) {
            canvas.drawPoint(value.x.toFloat(), value.y.toFloat(),circlePaint)
        }
    }
    private fun drawDash(canvas: Canvas) {

        for ((index, value) in mPoints.withIndex()) {
            canvas.drawLine(0f, 0f,mPoints[mPoints.size-1].x.toFloat(),0f,dashPaint)
            canvas.drawLine(paddingL.toFloat(),0f, paddingL.toFloat(),height.toFloat(),dashPaint)
            paddingL+=itemWidth
        }
    }
    private fun drawLine(canvas: Canvas){
        for ((index,value) in  mPoints.withIndex()){

            val w = value.x.toFloat()
            val h = value.y.toFloat()
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
        textPaint.getFontMetrics(fontMetrics)
        for ((index,value) in  mPoints.withIndex()){
            canvas.drawText(mList[index].value.toString()+"de",value.x.toFloat(), value.y.toFloat()-10.dp,textPaint)
            canvas.drawText(xAiasList[index].toMonthDate(), value.x.toFloat(),150.dp,textPaint)
        }
    }

    fun tempHeightPixel(tmp: Double): Float {
        val res = ((tmp - lowestTemp) / (highestTemp - lowestTemp) * (highestTempHeight - lowestTempHeight) + lowestTempHeight.toFloat()).toFloat()
        return defHeightPixel - res //y从上到下
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        velocityTracker.addMovement(event)
        when(event.actionMasked){
            MotionEvent.ACTION_DOWN->{
                downX = event.x
                downY = event.y
                downScrollX = scrollX
                isScrolling = false
            }
            MotionEvent.ACTION_MOVE->{
                val dx = (downX - event.x + downScrollX).toInt()
                scrollTo(dx,0)
            }
            MotionEvent.ACTION_UP->{
                velocityTracker.computeCurrentVelocity(1000, maxVelocity.toFloat())
                val vx = velocityTracker.xVelocity
                val scrollX = scrollX
                val target = if (abs(vx) <minVelocity){
                    if (scrollX<width/2f) 0 else 1
                }else{
                    if (vx<0) 1 else 0
                }

                val scrollDistance = if(target==1) width - scrollX else -scrollX
                overScroller.startScroll(scrollX,0,scrollDistance,0)
                postInvalidateOnAnimation()
            }
        }
        return true
    }
    override fun computeScroll() {
        if (overScroller.computeScrollOffset()){
            scrollTo(overScroller.currX,overScroller.currY)
            postInvalidateOnAnimation()
        }
        super.computeScroll()
    }

}