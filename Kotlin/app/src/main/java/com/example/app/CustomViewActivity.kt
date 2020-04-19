package com.example.app

import android.animation.*
import android.graphics.Point
import android.graphics.PointF
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import com.example.app.view.ProvinceEvaluator
//import com.example.app.view.ProvinceEvaluator
import com.example.app.view.TextTypeEvaluator
//import com.example.app.view.TextTypeEvaluator
import kotlinx.android.synthetic.main.activity_customview.*

class CustomViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customview)

        /* val animator = ObjectAnimator.ofFloat(view,"RADIUS",50f.px,150f.px)
         animator.duration = 2000
         animator.startDelay = 1000
         animator.start()*/

       /* val animator = ObjectAnimator.ofObject(view,"pointF",PointTypeEvaluator(),PointF(150f,150f),PointF(300f.px,300f.px))
        animator.duration = 2000
        animator.startDelay = 1000
        animator.start()*/


        /*val bottomCRoutationAnimator = ObjectAnimator.ofFloat(view,"bottomCRotation",60f)
        bottomCRoutationAnimator.duration = 1000
        bottomCRoutationAnimator.startDelay = 1000
        val canvasRotationAnimator = ObjectAnimator.ofFloat(view,"canvasRotation",270f)
        canvasRotationAnimator.duration = 1200
        val topCRotationAnimator = ObjectAnimator.ofFloat(view,"topCRotation",-60f)
        topCRotationAnimator.duration = 200

        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(bottomCRoutationAnimator,canvasRotationAnimator,topCRotationAnimator)
        animatorSet.start()*/

//        val keyFram0 = Keyframe.ofFloat(0f,0f)
//        val keyFram1 = Keyframe.ofFloat(2f,0.4f *fraction)
//        val keyFram0 = Keyframe.ofFloat(0f,0f)
//        val keyFram0 = Keyframe.ofFloat(0f,0f)


       /* val bottomCRoutationHolder = PropertyValuesHolder.ofFloat("bottomCRotation",60f)
        val canvasRotationHolder = PropertyValuesHolder.ofFloat("canvasRotation",270f)
        val topCRotationHolder = PropertyValuesHolder.ofFloat("topCRotation",-60f)
       val animator =  ObjectAnimator.ofPropertyValuesHolder(view,bottomCRoutationHolder,canvasRotationHolder,topCRotationHolder)
        animator.startDelay = 1000
        animator.duration = 3000
        animator.start()*/


//        val animator = ObjectAnimator.ofObject(view, "proviceName", TextTypeEvaluator(), "澳门特别行政区")
//        animator.startDelay = 1000
//        animator.duration = 10000
//        animator.start()


    }
}

class PointTypeEvaluator : TypeEvaluator<PointF> {
    override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
        var startX = startValue.x
        var startY = startValue.y
        var endX = endValue.x
        var endY = endValue.y
        var currentX = startX + (endX - startX) * fraction
        var currentY = startY + (endY - startY) * fraction
        return PointF(currentX, currentY)

    }

}