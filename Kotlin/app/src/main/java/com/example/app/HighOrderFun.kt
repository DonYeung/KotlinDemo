@file:Suppress("NAME_SHADOWING")

package com.example.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.text.StringBuilder

fun main(){
    val num1 = 100
    val num2 = 80
//    val result1 = numAndnum2(num1,num2,::plus)
//    val result2 = numAndnum2(num1,num2,::minus)
    val result1 = numAndnum2(num1,num2){ n1,n2->
       n1+n2
    }
    println(result1)


    val list = listOf("apple","banana","orange","pear","grape")
    val result = StringBuilder().build {
        append("start earting fruit \n")
        for(fruit in list){
            append(fruit).append("\n")
        }
        append("all fruits")
    }
    println(result.toString())


}

fun example(func: (String, Int) -> Unit) {
    func("hello", 123)
}

inline fun numAndnum2(num1: Int, num2: Int, opeartion: (Int, Int) -> Int): Int {
    val result = opeartion(num1, num2)
    return result
}

fun plus(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun minus(num1: Int, num2: Int): Int {
    return num1 + num2
}

inline fun StringBuilder.build(block:StringBuilder.() ->Unit ):StringBuilder{
    block()
    return this
}

//因为内联函数的lambda表达式中允许使用return关键字 和高阶函数的匿名类实现中不允许使用return关键字造成冲突，
//所以crossinline保证在内联函数的lambda表达式中一定不会使用return关键字，但仍然可以是u哦那个reutn@Runable进行
//局部返回
inline fun runRunnable(crossinline block: ()->Unit){
    val runnable = Runnable {
        block()
    }
}

