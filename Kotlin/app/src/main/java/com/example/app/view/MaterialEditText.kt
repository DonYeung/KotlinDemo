package com.example.app.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import com.example.app.R
import com.example.app.utils.px

class MaterialEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    private val HORIZONTAL_OFFSET = 5f.px
    private val VERITAL_OFFSET = 20f.px
    private val MOVE_OFFSET = 16F.px
    private val TEXT_SIZE = 12F.px
    private val TEXT_MARGIN = 8F.px
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = TEXT_SIZE
    }

    //todo
    private val animator  by lazy {ObjectAnimator.ofFloat(this,"floatAnimatorFraction",0f,1f)}
    private var isShow = false
    var floatAnimatorFraction = 0f
        set(value) {
                field = value
                invalidate()

        }

    var useFloatLabel = false
        set(value) {
            if(field!=value) {
                field = value
                if (field){
                    setPadding((HORIZONTAL_OFFSET+paddingLeft).toInt(), (paddingTop+TEXT_SIZE+TEXT_MARGIN).toInt(),paddingRight,paddingBottom)
                }else {
                    setPadding((HORIZONTAL_OFFSET+paddingLeft).toInt(), (paddingTop-TEXT_SIZE-TEXT_MARGIN).toInt(),paddingRight,paddingBottom)

                }
                invalidate()
            }
        }

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        useFloatLabel = typeArray.getBoolean(R.styleable.MaterialEditText_useFloatLabel,true)
//        val typeArray = context.obtainStyledAttributes(attrs,)
//        setPadding(paddingLeft, (paddingTop+TEXT_SIZE+TEXT_MARGIN).toInt(),paddingRight,paddingBottom)
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (text.isNullOrEmpty() && isShow){
            isShow = false
            //todo
            animator.reverse()
        }else if(!text.isNullOrEmpty() && !isShow){
            isShow = true
            animator.start()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.alpha = (floatAnimatorFraction * 0xff).toInt()
        var  currentPostion = VERITAL_OFFSET+ MOVE_OFFSET * (1-floatAnimatorFraction)
        canvas.drawText(hint.toString(),HORIZONTAL_OFFSET,currentPostion,paint)
    }
}