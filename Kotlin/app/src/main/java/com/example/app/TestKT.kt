package com.example.app

import java.lang.StringBuilder

fun main() {

    val fruits = listOf("apple", "banana", "pear", "grape", "watermelon")

   /* val maxresult = fruits.maxBy { it.length }
    println(maxresult)*/

   val lambda = {fruit :String ->fruits.size}
//    val maxLength = fruits.maxBy(lambda)
        //or
//    val maxLength = fruits.maxBy({fruit :String ->fruits.size})
//    val maxLength = fruits.maxBy(){fruit :String ->fruits.size}
//    val maxLength = fruits.maxBy{fruit :String ->fruits.size}
//    val maxLength = fruits.maxBy{fruits.size}
//    val maxLength = fruits.maxBy{it.length}



    /*val buttonKT = ButtonKT()
    buttonKT.setButtonClickListener(object : ButtoClickListener {
        override fun toClick() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    })*/



    /*val newList = fruits.map{
        it.toUpperCase()
    }
    for (fruit in newList){
        println(fruit)
    }
*/

   /* val newList = fruits.filter { it.length<=5 }
            .map{it.toUpperCase()}
    for (fruit in newList){
        println(fruit)
    }*/

    val anyresult = fruits.any { it.length<=5 }
    val allres = fruits.all { it.length<=5 }
    println("any:$anyresult,all:$allres")


}

/*
fun getTextLength(text :String?):Int{
    if (text!=null){
        return text.length
    }
    return 0
}*/
//简化后
fun getTextLength(text :String?):Int = text?.length ?: 0


fun setButtonClicker(l:(ButtonKT)->Unit){

}

interface ButtoClickListener {
    fun toClick()
}
class ButtonKT{
    fun setButtonClickListener(listener: ButtoClickListener){
        listener.toClick()
    }
}


fun test() {
    val fruits = listOf("apple", "banana", "pear", "grape", "watermelon")
    val builder = StringBuilder()
    builder.append("start eating fruits. \n")
    for (fruit in fruits) {
        builder.append(fruit).append("\n")
    }
    builder.append("eat all end ")
    val result = builder.toString()
    println(result)

    //简化 使用with
    val res = with(StringBuilder()) {
        append("start eating fruits. \n")
        for (fruit in fruits) {
            append(fruit).append("\n")
        }
        append("eat all end ")
        toString()
    }
    println(res)

//    使用run简化
    val res1 = StringBuilder().run {
        append("start eating fruits. \n")
        for (fruit in fruits) {
            append(fruit).append("\n")
        }
        append("eat all end ")
        toString()
    }
    println(res1)

    //使用apply简化 apply 无法指定返回值，二十自动返回调用本身
    val res2 = StringBuilder().apply {
        append("start eating fruits. \n")
        for (fruit in fruits) {
            append(fruit).append("\n")
        }
        append("eat all end ")
    }
    println(res2)

}
