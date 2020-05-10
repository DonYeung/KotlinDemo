package com.example.app.materialdemo

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.adapter.FruitAdapter
import com.example.app.adapter.FruitRecycleAdapter
import com.example.app.entity.Fruit
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_materiral_layout.*
import kotlin.concurrent.thread

class NavigationViewDemo:AppCompatActivity() {

    private val fruitList = mutableListOf<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materiral_layout)
        initFruit()

        setSupportActionBar(toolbar)
        navView.setCheckedItem(R.id.nav1)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_settings)
        }
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }

        //悬浮按钮
        fab.setOnClickListener{
//            Toast.makeText(this,"fab onclick",Toast.LENGTH_SHORT).show()
            //Snackbar
            Snackbar.make(it,"snack bar onclick",Snackbar.LENGTH_SHORT)
                    .setAction("undo"){
                        Toast.makeText(this,"data restored",Toast.LENGTH_SHORT).show()
                    }
                    .show()

        }

        val adapter  = FruitRecycleAdapter(fruitList)
//        val lineManager = LinearLayoutManager(this)
//        lineManager.orientation = LinearLayoutManager.VERTICAL
        val gridLayoutManager = GridLayoutManager(this,2)
        recycleview.layoutManager = gridLayoutManager
        recycleview.adapter = adapter

        //swipeRefresh下拉刷新
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        swipeRefresh.setOnRefreshListener {
            refreshFruit(adapter)
        }

    }

    private fun refreshFruit(adapter:FruitRecycleAdapter){
        thread {
            Thread.sleep(3000)
            runOnUiThread {
                initFruit()
                adapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.backup->{
                Toast.makeText(this,"backup", Toast.LENGTH_SHORT).show()}
            R.id.delete->{
                Toast.makeText(this,"delete", Toast.LENGTH_SHORT).show()}
            R.id.settings->{
                Toast.makeText(this,"settings", Toast.LENGTH_SHORT).show()}
            android.R.id.home->  {
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show()
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return true
    }

    private fun initFruit(){

        fruitList.add(Fruit("apple",R.drawable.iv_avatar))
        fruitList.add(Fruit("banana",R.drawable.iv_avatar))
        fruitList.add(Fruit("orange",R.drawable.iv_avatar))
        fruitList.add(Fruit("watermelon",R.drawable.iv_avatar))
        fruitList.add(Fruit("apple",R.drawable.iv_avatar))
        fruitList.add(Fruit("banana",R.drawable.iv_avatar))
        fruitList.add(Fruit("orange",R.drawable.iv_avatar))
        fruitList.add(Fruit("watermelon",R.drawable.iv_avatar))
        fruitList.add(Fruit("apple",R.drawable.iv_avatar))
        fruitList.add(Fruit("banana",R.drawable.iv_avatar))
        fruitList.add(Fruit("orange",R.drawable.iv_avatar))
        fruitList.add(Fruit("watermelon",R.drawable.iv_avatar))
        fruitList.add(Fruit("apple",R.drawable.iv_avatar))
        fruitList.add(Fruit("banana",R.drawable.iv_avatar))
        fruitList.add(Fruit("orange",R.drawable.iv_avatar))
        fruitList.add(Fruit("watermelon",R.drawable.iv_avatar))
        fruitList.add(Fruit("apple",R.drawable.iv_avatar))
        fruitList.add(Fruit("banana",R.drawable.iv_avatar))
        fruitList.add(Fruit("orange",R.drawable.iv_avatar))
        fruitList.add(Fruit("watermelon",R.drawable.iv_avatar))
        fruitList.add(Fruit("banana",R.drawable.iv_avatar))
        fruitList.add(Fruit("orange",R.drawable.iv_avatar))
        fruitList.add(Fruit("watermelon",R.drawable.iv_avatar))
        fruitList.add(Fruit("apple",R.drawable.iv_avatar))
        fruitList.add(Fruit("banana",R.drawable.iv_avatar))
        fruitList.add(Fruit("orange",R.drawable.iv_avatar))
        fruitList.add(Fruit("watermelon",R.drawable.iv_avatar))
        fruitList.add(Fruit("apple",R.drawable.iv_avatar))
        fruitList.add(Fruit("banana",R.drawable.iv_avatar))
        fruitList.add(Fruit("orange",R.drawable.iv_avatar))
        fruitList.add(Fruit("watermelon",R.drawable.iv_avatar))
        fruitList.add(Fruit("banana",R.drawable.iv_avatar))
        fruitList.add(Fruit("orange",R.drawable.iv_avatar))
        fruitList.add(Fruit("watermelon",R.drawable.iv_avatar))
        fruitList.add(Fruit("apple",R.drawable.iv_avatar))
        fruitList.add(Fruit("banana",R.drawable.iv_avatar))
        fruitList.add(Fruit("orange",R.drawable.iv_avatar))
        fruitList.add(Fruit("watermelon",R.drawable.iv_avatar))
        fruitList.add(Fruit("apple",R.drawable.iv_avatar))
        fruitList.add(Fruit("banana",R.drawable.iv_avatar))
        fruitList.add(Fruit("orange",R.drawable.iv_avatar))
        fruitList.add(Fruit("watermelon",R.drawable.iv_avatar))
    }

}