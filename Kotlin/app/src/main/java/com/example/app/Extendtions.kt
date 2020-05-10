package com.example.app

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.lang.RuntimeException
import java.time.Duration

val Float.px
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this, Resources.getSystem().displayMetrics)
val Int.px
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this.toFloat(),Resources.getSystem().displayMetrics)

fun SharedPreferences.open(block:(SharedPreferences.Editor.() -> Unit)){
    val editer = edit()
    editer.block()
    editer.apply()
}

val Float.dp
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this,Resources.getSystem().displayMetrics)

val Int.dp
    get() = this.toFloat().dp


fun getAvatar(res:Resources,size:Int):Bitmap{
    val option = BitmapFactory.Options()
    option.inJustDecodeBounds = true
    BitmapFactory.decodeResource(res,R.drawable.iv_avatar,option)
    option.inJustDecodeBounds = false
    option.inDensity = option.outWidth
    option.inTargetDensity = size
    return  BitmapFactory.decodeResource(res,R.drawable.iv_avatar,option)
}

fun cvOf(vararg pairs:Pair<String,Any?>):ContentValues{
    val cv = ContentValues()
    for(pair in pairs){
        val key = pair.first
        val value = pair.second
        when(value){
            is Int -> cv.put(key,value)
            is Short -> cv.put(key,value)
            is Float -> cv.put(key,value)
            is Double -> cv.put(key,value)
            is String -> cv.put(key,value)
            is Byte -> cv.put(key,value)
            is ByteArray -> cv.put(key,value)
            null -> cv.putNull(key)
        }

    }
    return cv
}



infix fun <T> Collection<T>.has(element:T) = contains(element)

fun main(){
    val list = listOf("apple","grape","pear","banana","orange")
//    if (list.contains("apple"))
//    if (list has "apple")
}

inline fun <reified T> startActivity(context:Context){
    val intent = Intent(context,T::class.java)
    context.startActivity(intent)
}

inline fun <reified T>startActivity(context: Context,block:Intent.()->Unit){
    val intent = Intent(context,T::class.java)
    intent.block()
    context.startActivity(intent)
}

//这里的T类型必须是可比较的 所以必须实现Comparable接口
fun <T:Comparable<T>> max(vararg nums:T):T{
    if(nums.isEmpty()) throw RuntimeException("Params can not be empty")
    var maxnum = nums[0]
    for (num in nums){
        if (num>maxnum){
            maxnum = num
        }
    }
    return maxnum
}
fun String.showToast(context: Context,duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(context,this,duration).show()
}
fun Int.showToast(context: Context,duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(context,this.toString(),duration).show()
}

fun View.showSnackBar(text:String,actionText:String? = null ,duration: Int=Snackbar.LENGTH_SHORT,block:(()->Unit)? = null){
    val snackbar = Snackbar.make(this,text,duration)
    if (actionText!=null && block!=null){
        snackbar.setAction(actionText){
            block()
        }
    }
    snackbar.show()
}
