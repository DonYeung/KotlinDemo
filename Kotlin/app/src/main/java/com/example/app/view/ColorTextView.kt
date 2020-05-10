package com.example.app.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.example.app.px
import java.util.*

class ColorTextView(context: Context?, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    private val colorList = intArrayOf(Color.parseColor("#3F51B5"), Color.parseColor("#2196F3"), Color.parseColor("#009688"),
            Color.parseColor("#FF9800"), Color.parseColor("#FF5722"), Color.parseColor("#795548")
    )
    private val textsizeList = intArrayOf(16, 20, 22)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val random = Random()
    private val X_PADDING = 16F.px.toInt()
    private val Y_PADDING = 8F.px.toInt()
    private val ROUNDS = 5F.px

    init {

        setTextColor(Color.WHITE)
        paint.color = colorList[random.nextInt(colorList.size)]
        textSize = textsizeList[random.nextInt(3)].toFloat()
        setPadding(X_PADDING, Y_PADDING, X_PADDING, Y_PADDING)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), ROUNDS, ROUNDS, paint)

        super.onDraw(canvas)
    }
}