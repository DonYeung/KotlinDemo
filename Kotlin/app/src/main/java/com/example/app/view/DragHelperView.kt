package com.example.app.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.DragStartHelper
import androidx.core.view.ViewCompat
import androidx.core.view.children
import androidx.customview.widget.ViewDragHelper

/**
 * 自定义拖拽-ViewDragHelper （关注于界面）
 */
private const val ROWS = 3
private const val COLUMNS = 2
class DragHelperView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private val mydraghelpercallback = MyDragHelperCallback()
    private var capturedChildLeft = 0
    private var capturedChildTop = 0

    private val dragHelper = ViewDragHelper.create(this, mydraghelpercallback)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)
        val childWidth = widthSpecSize / COLUMNS
        val childwHeight = heightSpecSize / ROWS
        measureChildren(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(childwHeight, MeasureSpec.EXACTLY))
        setMeasuredDimension(widthSpecSize, heightSpecSize)

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        var childTop = 0
        val childWidth = width/ COLUMNS
        val childHeight = height / ROWS
        for ((index, child) in children.withIndex()) {
//            if (index%2 == 0) childLeft = width else 0
//            if(ROWS==1)  childTop = 0
//            else if(ROWS==2) childTop =  height*2
//            else if(ROWS==3) childTop =  height*3
            childLeft = index % 2 * childWidth
            childTop = index / 2 * childHeight
            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight)
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return dragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        dragHelper.processTouchEvent(event)
        return true
    }

    inner class MyDragHelperCallback : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }

        /**
         * clampViewPosition 限制哪个方向哪个方向才可以活动  默认为0，返回top/left 上/左才可以活动
         */
        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }
        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return left
        }

        //可做位置重排
        override fun onViewPositionChanged(changedView: View, left: Int, top: Int, dx: Int, dy: Int) {
            super.onViewPositionChanged(changedView, left, top, dx, dy)
        }

        //当view被抓住时，  可做初始化
        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
//            super.onViewCaptured(capturedChild, activePointerId)
            capturedChild.elevation  = elevation+1
            capturedChildLeft = capturedChild.left
            capturedChildTop = capturedChild.top
        }

        override fun onViewDragStateChanged(state: Int) {
            super.onViewDragStateChanged(state)

        }
        //松手的操作
        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
            dragHelper.settleCapturedViewAt(capturedChildLeft,capturedChildTop)
            postInvalidateOnAnimation()
        }
    }

    override fun computeScroll() {
        if(dragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this)
        }
        super.computeScroll()
    }
}