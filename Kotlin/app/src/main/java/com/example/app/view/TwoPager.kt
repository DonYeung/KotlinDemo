package com.example.app.view

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.view.*
import android.widget.OverScroller
import androidx.core.view.children
import kotlin.math.abs

class TwoPager(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private val viewConfig = ViewConfiguration.get(context)
    private val minVelocity = viewConfig.scaledMinimumFlingVelocity
    private val maxVelocity = viewConfig.scaledMaximumFlingVelocity
    private val touchSlop = viewConfig.scaledPagingTouchSlop
    private val velocityTracker = VelocityTracker.obtain()
    private var downX = 0f
    private var downY = 0f
    private var downScrollX = 0
    private var isScrolling = false
    private val overScroller = OverScroller(context)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec,heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        val childTop = 0
        var childRight =width
        val childBottom = height
        for(child in children){
            child.layout(childLeft,childTop,childRight,childBottom)
            childLeft += width
            childRight += width
        }
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        velocityTracker.addMovement(event)
        var result = false
        when(event.actionMasked){
            MotionEvent.ACTION_DOWN->{
                downX = event.x
                downY = event.y
                downScrollX = scrollX
                isScrolling = false
            }
            MotionEvent.ACTION_MOVE->{
                if (!isScrolling){
                    val dx = downX - event.x
                    if (abs(dx)>touchSlop){
                        result = true
                        parent.requestDisallowInterceptTouchEvent(true)
                        isScrolling = true
                    }
                }
            }
        }
        return result
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
                val target = if (abs(vx)<minVelocity){
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