package com.example.app.view

import android.content.ClipData
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import kotlinx.android.synthetic.main.activity_draglistener_view.view.*

/**
 * 自定义拖拽-OnDragListener 可跨进程拖拽数据（关注于数据）
 */
class DragListenerView(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    private val onDragListener = MyDragListener()
    private val onLongClickListener= OnLongClickListener {
        val clipData = ClipData.newPlainText("data",it.contentDescription)
        ViewCompat.startDragAndDrop(it,clipData, DragShadowBuilder(it),it,0)
    }
    override fun onFinishInflate() {
        super.onFinishInflate()
        imageView.setOnLongClickListener(onLongClickListener)
        imageView2.setOnLongClickListener(onLongClickListener)
        contentLayout.setOnDragListener(onDragListener)
    }

    inner class MyDragListener :OnDragListener{
        override fun onDrag(v: View?, event: DragEvent): Boolean {
            when(event.action){
                DragEvent.ACTION_DROP->{
                    if (v is LinearLayout){
                        val clipData = event.clipData
                        val textView = TextView(context)
                        textView.setTextColor(Color.BLUE)
                        textView.textSize = 16f
//                        textView.text = clipData.getItemAt(0).text
                        textView.text = clipData.toString()
                         Toast.makeText(context,clipData.getItemAt(0).text,Toast.LENGTH_SHORT).show()
                        contentLayout.addView(textView)
//                        invalidate()
                    }
                }
            }
            return true
        }
    }


}