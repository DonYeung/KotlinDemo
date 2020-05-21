package com.example.app.viewmodeldemo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.app.R
import kotlinx.android.synthetic.main.activity_test.*

class DemoViewModelActivity : AppCompatActivity() {
    val myviewModel: DemoViewModel by lazy {
        //第一种写法 hencoder,由by viewmodels（）衍生
        viewModels<DemoViewModel>() {
            DemoViewModelFactory(2121)
        }.value
        //废弃   //第二种写法，第一行代码
//        ViewModelProviders.of(this,DemoViewModelFactory(2121)).get(DemoViewModel::class.java)

        //第三种写法 hencoder
        /*val model: DemoViewModel by viewModels(){
            DemoViewModelFactory(2121)
        }
        model*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

//        viViewModelProvider.Factory
//        myviewModel =

//        myviewModel = ViewModelProviders.of(this).get(DemoViewModel::class.java)
        refresh()

        btn.setOnClickListener {
            myviewModel.counter += 1
            refresh()
        }


    }

    private fun refresh() {
        text.text = myviewModel.counter.toString()

    }

}