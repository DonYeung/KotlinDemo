package com.example.app.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.math.max

class TagLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private val childrensBounds = mutableListOf<Rect>()


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        var widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        var heightSpecMode = MeasureSpec.getSize(heightMeasureSpec)
        var heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)

        var widthUsed = 0
        var heightUsed = 0
        var lineMaxHeight = 0
        var lineWidthUsed = 0

        for ((index,child) in children.withIndex()){
            measureChildWithMargins(child,widthMeasureSpec,0 , heightMeasureSpec,heightUsed)

            if(widthSpecMode!=MeasureSpec.UNSPECIFIED &&
                    lineWidthUsed+child.measuredWidth >= widthSpecSize){
                lineWidthUsed = 0
                heightUsed += lineMaxHeight
                lineMaxHeight = 0
                measureChildWithMargins(child,widthMeasureSpec,0 , heightMeasureSpec,heightUsed)
            }

            if(childrensBounds.size<=index) {
                childrensBounds.add(Rect())
            }
            val childBound = childrensBounds[index]
            childBound.set(lineWidthUsed, heightUsed, lineWidthUsed+child.measuredWidth, heightUsed+child.measuredHeight)

            lineWidthUsed += child.measuredWidth
            widthUsed = max(widthUsed,lineWidthUsed)
            lineMaxHeight = max(lineMaxHeight,child.measuredHeight)
        }
        val selfWidth = widthUsed
        val selfHeight = lineMaxHeight + heightUsed
        setMeasuredDimension(selfWidth,selfHeight)
    }
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for ((index,child) in children.withIndex()){
//            child.layout(0f,0f,childrensBounds.)
            val childBound = childrensBounds[index]
            child.layout(childBound.left,childBound.top,childBound.right,childBound.bottom)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
//        return super.generateLayoutParams(attrs)
        return MarginLayoutParams(context,attrs)
    }
}