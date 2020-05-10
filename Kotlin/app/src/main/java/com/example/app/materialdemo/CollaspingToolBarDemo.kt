package com.example.app.materialdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.app.R
import kotlinx.android.synthetic.main.activity_materiral_layout2.*

/**
 * 可折叠标题栏布局
 */
class CollaspingToolBarDemo:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materiral_layout2)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
        collapsingToobar.title = "哈哈"
        Glide.with(this).load(R.drawable.ic_avatar).into(fruitImageView)
        fruitContentText.text = "xxaqqrqwq".repeat(500)
    }
}