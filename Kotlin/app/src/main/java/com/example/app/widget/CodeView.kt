package com.example.app.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.example.app.R
import com.example.core.utils.Utils
import com.example.core.utils.dp2px
import java.util.*
import kotlin.random.Random as Random1

class CodeView constructor(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = getContext().getColor(R.color.colorAccent)
        strokeWidth = 6f.dp2px()
    }

    private var codeList = arrayOf("kotlin",
            "android",
            "java",
            "http",
            "https",
            "okhttp",
            "retrofit",
            "tcp/ip")

    constructor(context: Context) : this(context, null)

    init {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        gravity = Gravity.CENTER
        setBackgroundColor(getContext().getColor(R.color.colorPrimary))
        setTextColor(Color.WHITE)


        updateCode()
    }


    fun updateCode() {
        var random = Random().nextInt(codeList.size)
        val code = codeList[random]
        text = code
    }

    interface onClickListenerr{
        fun onclick(view: View)
    }


    override fun onDraw(canvas: Canvas?) {
        canvas?.drawLine(0f, height.toFloat(), width.toFloat(), 0f, paint)
        super.onDraw(canvas)

    }
}