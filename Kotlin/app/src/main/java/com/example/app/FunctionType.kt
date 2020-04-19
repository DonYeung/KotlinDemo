package com.example.app

import android.widget.Toolbar

class FunctionType {

}

class View{
    fun setOnclickListener(listener: (View)->Unit){

    }
}
fun main(){
    val view =View()
    view.setOnclickListener(::OnClick)
}

fun OnClick(view :View){
    println("bei dianji le ")
}