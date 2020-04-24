package com.example.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HighOrderFun :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)



        val buttonE = ButtonE()
        buttonE.setToClickLiener(object : ButtonClickListener {
            override fun toClick() {

            }
        })
        //无法通过labmda 的方式创建
        /*buttonE.setToClickLiener{

        }*/

        stringMapper(""){
            it.length
        }



    }
    fun aasda(a : Int){

    }
    fun stringMapper(str: String, mapper: (String) -> Int): Int {
        // Invoke function

        (::aasda).invoke(1)
        return mapper(str)
    }

    fun setToClickLienerKT(l: ButtonClickListener,listener: (ButtonClickListener)->Unit){
        listener(l)
    }
}

class ButtonE {
    fun setToClickLiener(listener: ButtonClickListener){
        listener.toClick()
    }
}
interface ButtonClickListener {
    fun toClick()
}