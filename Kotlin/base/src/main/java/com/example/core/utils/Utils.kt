@file:JvmName("KotlinUtils")

package com.example.core.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.example.core.BaseApplication

fun Float.dp2px() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this@dp2px, Utils.displayMetrics)


object Utils {
    val displayMetrics = Resources.getSystem().displayMetrics


    /*fun toast(string :String? ){
        toast(string, Toast.LENGTH_SHORT);

    }*/

    // 使用 = 加入默认值
    // 可传参2。不传则为默认
    fun toast(string: String?, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(BaseApplication.currentApplication, string, duration).show();
    }

}