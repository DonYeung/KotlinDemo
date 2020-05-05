package com.example.app.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.app.getAvatar
import com.example.app.utils.dp

/**
 * 接力型--多点触摸
 */
class MultiTouchView1(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(resources, 200.dp.toInt())
    private var orinalOffsetX = 0f
    private var orinalOffsetY = 0f
    private var offsetX = 0f
    private var offsetY = 0f
    private var downX = 0f
    private var downY = 0f
    private var trackingId = 0
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap,offsetX,offsetY,paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.actionMasked){
            MotionEvent.ACTION_DOWN ->{
                trackingId = event.getPointerId(0)
                downX = event.x
                downY = event.y
                orinalOffsetX = offsetX
                orinalOffsetY = offsetY
            }
            MotionEvent.ACTION_POINTER_DOWN->{
                val actionIndex = event.actionIndex
                trackingId = event.getPointerId(actionIndex)
                downX = event.getX(actionIndex)
                downY = event.getY(actionIndex)
                orinalOffsetX = offsetX
                orinalOffsetY = offsetY

            }
            MotionEvent.ACTION_POINTER_UP->{
                val newIndex = event.actionIndex
                if (event.getPointerId(newIndex) == trackingId){
                    val actionIndex = if(newIndex == event.pointerCount- 1){
                        event.pointerCount - 2
                    }else{
                        event.pointerCount - 1
                    }
                    trackingId = event.getPointerId(actionIndex)
                    downX = event.getX(actionIndex)
                    downY = event.getY(actionIndex)
                    orinalOffsetX = offsetX
                    orinalOffsetY = offsetY

                }
            }
            MotionEvent.ACTION_MOVE ->{
                val index = event.findPointerIndex(trackingId)
                offsetX = event.getX(index) - downX + orinalOffsetX
                offsetY = event.getY(index) - downY + orinalOffsetY
                invalidate()
            }
        }

        return true
    }
}