package com.example.app.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import com.example.app.dp

/**
 * 多点触摸--各自为战(画图工具)
 */
class MultiTouchView3(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    private var paths = SparseArray<Path>()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 3.dp
        style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for(i in 0 until paths.size()){
            val path = paths.valueAt(i)
            canvas.drawPath(path,paint)

        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.actionMasked){
            MotionEvent.ACTION_DOWN,MotionEvent.ACTION_POINTER_DOWN->{

                val actionIndex = event.actionIndex
                val path = Path()
                path.moveTo(event.getX(actionIndex),event.getY(actionIndex))

                val  pointerId = event.getPointerId(actionIndex)
                paths.append(pointerId,path)
                invalidate()

            }
            MotionEvent.ACTION_MOVE->{
                for(i in 0 until paths.size()){
                    val poiterId = event.getPointerId(i)
                    val path = paths.get(poiterId)
                    path.lineTo(event.getX(i),event.getY(i))
                }
                invalidate()

            }

            MotionEvent.ACTION_UP,MotionEvent.ACTION_POINTER_UP->{
                val actionIndex = event.actionIndex
                val potinterId = event.getPointerId(actionIndex)
                paths.remove(potinterId)
                invalidate()

            }
        }
        return true
    }
}