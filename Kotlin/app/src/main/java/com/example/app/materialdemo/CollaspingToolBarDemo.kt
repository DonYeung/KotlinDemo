package com.example.app.materialdemo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.example.app.R
import com.example.app.showToast
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
        collapsingToobar.title = "扔物线"
        Glide.with(this).load(R.drawable.ic_avatar3).into(fruitImageView)
        fruitContentText.text = "xxaqqrqwq".repeat(500)
        fab_btn.setOnClickListener {
            "hahhaha".showToast(this)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }
}