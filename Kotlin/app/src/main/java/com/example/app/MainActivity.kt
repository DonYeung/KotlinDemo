package com.example.app

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.app.entity.User
import com.example.app.widget.CodeView
import com.example.core.utils.CacheUtils
import com.example.core.utils.Utils
import com.example.lesson.LessonActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import okhttp3.Request
import okhttp3.Response

class MainActivity : Activity(), View.OnClickListener {
    val usernameKey = "username"
    val passwordKey = "password"

    lateinit var et_username: EditText
    lateinit var et_password: EditText
    lateinit var et_code: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_code = findViewById(R.id.et_code)

        et_username.setText(CacheUtils.get(usernameKey))
        et_password.setText(CacheUtils.get(passwordKey))


        val btn_login: Button = findViewById(R.id.btn_login)
        val img_code: CodeView = findViewById(R.id.code_view)

        btn_login.setOnClickListener(this)
        img_code.setOnClickListener(this)

//        username.setText()
    }

    override fun onClick(v: View?) {
        if (v is CodeView) {
            v.updateCode()
        } else if (v is Button) {
            login()
        }
    }

    fun login() {
        val username = et_username.text.toString()
        val password = et_password.text.toString()
        val code = et_code.text.toString()


        //这里user类转为data class 可直接使用copy hascode 等
        val user  = User(username,password,code)

        //这里还是不使用内部函数
        if (verify(user)){
            CacheUtils.save(usernameKey, username)
            CacheUtils.save(passwordKey, password)
            startActivity(Intent(this,LessonActivity::class.java))

        }
    }


    fun verify(user: User):Boolean{

        /*
        if (user.username!=null && user.username!!.length<4){
            Utils.toast("用户名不合法");
            return false
        }
        if(user.password!=null && user.password!!.length<4){
            Utils.toast("密码不合法");
            return false
        }
        */
        if(user.username?.length?:0 <4){
            Utils.toast("用户名不合法");
            return false
        }
        if(user.password?.length ?:0 <4){
            Utils.toast("密码不合法");
            return false
        }
        return true
    }

}