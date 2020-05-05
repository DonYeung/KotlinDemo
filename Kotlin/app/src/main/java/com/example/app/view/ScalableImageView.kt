package com.example.app.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.animation.doOnEnd
import androidx.core.view.GestureDetectorCompat
import com.example.app.R
import com.example.app.px
import kotlin.math.max
import kotlin.math.min

private val IMAGE_SIZE = 300f.px
private const val EXTRA_SCALE_FACTOR = 1.5f

class ScalableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(IMAGE_SIZE.toInt())
    private var orinalOffsetX = 0f
    private var orinalOffsetY = 0f
    private var offsetX = 0f
    private var offsetY = 0f
    private var isBig = false
    private var smallScale = 0f
    private var bigScale = 0f
    private val gestureListener = MyGestureListener()
    private val gestureDetector = GestureDetectorCompat(context, gestureListener)
    private val scalGestureListener = MyScaleGestureListener()
    private val scalGestureDetector = ScaleGestureDetector(context, scalGestureListener)
    private val scroller = OverScroller(context)
    private var currentScale = 0f
        set(value) {
            field = value
            invalidate()
        }
    /*private var scaleFraction = 0f
        set(value) {
            field = value
            invalidate()
        }*/
    private val animator = ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale)


    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        return gestureDetector.onTouchEvent(event)
//        return scalGestureDetector.onTouchEvent(event)

        scalGestureDetector.onTouchEvent(event)
        if (!scalGestureDetector.isInProgress) {
            gestureDetector.onTouchEvent(event)
        }
        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        orinalOffsetX = (width - IMAGE_SIZE) / 2f
        orinalOffsetY = (height - IMAGE_SIZE) / 2f
        if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
            smallScale = width / bitmap.width.toFloat()
            bigScale = height / bitmap.height.toFloat() * EXTRA_SCALE_FACTOR
        } else {
            smallScale = height / bitmap.height.toFloat()
            bigScale = width / bitmap.width.toFloat() * EXTRA_SCALE_FACTOR
        }
        currentScale = smallScale
        animator.setFloatValues(smallScale, bigScale)


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val scaleFraction = (currentScale - smallScale) / (bigScale - smallScale)
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, orinalOffsetX, orinalOffsetY, paint)


//        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)
//        val scale = smallScale + (bigScale - smallScale) * scaleFraction
//        canvas.scale(scale, scale, width / 2f, height / 2f)
//        canvas.drawBitmap(bitmap, orinalOffsetX, orinalOffsetY, paint)

    }

    //输出图片的宽高= 原图片的宽高 / inSampleSize * (inTargetDensity / inDensity)
    private fun getAvatar(width: Int): Bitmap {
        val option = BitmapFactory.Options()
        option.inJustDecodeBounds = true //不加载到内存
        BitmapFactory.decodeResource(this.resources, R.drawable.ic_avatar2, option)
        option.inJustDecodeBounds = false //不加载到内存
        option.inDensity = option.outWidth
        option.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.ic_avatar2, option)
    }

    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener(), Runnable {
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(downEvent: MotionEvent, currentEvent: MotionEvent, velocityX: Float, velocityY: Float): Boolean {

            if (isBig) {
                val maxX = ((bitmap.width * bigScale - width) / 2).toInt()
                val minX = (-(bitmap.width * bigScale - width) / 2).toInt()
                val maxY = ((bitmap.height * bigScale - height) / 2).toInt()
                val minY = (-(bitmap.height * bigScale - height) / 2).toInt()
                scroller.fling(offsetX.toInt(), offsetY.toInt(), velocityX.toInt(), velocityY.toInt(), minX, maxX, minY, maxY)
                postOnAnimation(this)

            }
            return false
        }

        override fun run() {
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.currX.toFloat()
                offsetY = scroller.currY.toFloat()
                invalidate()
                postOnAnimation(this)
            }

        }

        override fun onScroll(downEvent: MotionEvent?, currentEvent: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            if (isBig) {
                offsetX -= distanceX
                offsetY -= distanceY
                fixOffset()

                invalidate()

            }
            return false
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            isBig = !isBig
            if (isBig) {
                offsetX = (e.x - width / 2f) * (1 - bigScale / smallScale)
                offsetY = (e.y - height / 2f) * (1 - bigScale / smallScale)

                fixOffset()
                animator.start()
            } else {
                currentScale = smallScale + (bigScale-smallScale) * animator.animatedFraction
                animator.reverse()
            }
//            invalidate()
            return true
        }



    }
    private fun fixOffset() {
        offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
        offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2)

        offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2)
        offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2)
    }

    inner class MyScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener {
        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            offsetX = (detector.focusX - width / 2f) * (1 - bigScale / smallScale)
            offsetY = (detector.focusY - height / 2f) * (1 - bigScale / smallScale)
            fixOffset()
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
//            val focusX = detector.focusX
            val tempScale = currentScale * detector.scaleFactor
            if (tempScale < smallScale || tempScale > bigScale) {
                return false
            } else {
                currentScale *= detector.scaleFactor
                currentScale = currentScale.coerceAtLeast(smallScale).coerceAtMost(bigScale)
                if(currentScale>smallScale){
//                    offsetX = (detector.focusX - width / 2f) * (1 - bigScale / smallScale)
//                    offsetY = (detector.focusY - height / 2f) * (1 - bigScale / smallScale)
//                    fixOffset()
                    isBig = true
                }else{
                    isBig = false
                }
                return true
            }
            /* if(currentScale<smallScale){
                 currentScale = smallScale
             }else if(currentScale>bigScale){
                 currentScale = bigScale
             }*/
            //为true则是跟上一秒的比值  为false则是跟初始的比值
        }

    }


}