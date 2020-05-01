package com.example.app

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import com.example.app.adapter.FruitAdapter
import com.example.app.adapter.FruitRecycleAdapter
import com.example.app.entity.Fruit
import kotlinx.android.synthetic.main.activity_listview.*
import kotlinx.android.synthetic.main.activity_test.*

class FirstActivity : AppCompatActivity() {
    private val data = listOf("apple","banana","orange","watermelon","pear","grape",
            "pineaoole","cherry","stawberry","lemon","mango","apple","banana","orange","watermelon",
            "pear","grape","pineaoole","cherry","stawberry","lemon","mango")

    private val fruitList = mutableListOf<Fruit>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        supportActionBar?.hide()
        setContentView(R.layout.activity_listview)

        initFruit()
//        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
//        listview.adapter = adapter


       /* val adapter = FruitAdapter(this,R.layout.fruit_item,fruitList)
        listview.adapter = adapter

        listview.setOnItemClickListener { _, _, position, _ ->
            val fruit = fruitList[position]
            Toast.makeText(this,fruit.name,Toast.LENGTH_SHORT).show()
        }*/


        val adapter = FruitRecycleAdapter(fruitList)
        recycleview.adapter = adapter

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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> {
//                val intent = Intent()
//                intent.setClass(this,ThirdActivity::class.java)
////                intent.action = Intent.ACTION_VIEW
////                intent.data = Uri.parse("https://www.baidu.com")
//                /*intent.action = "com.example.app.activity.ACTION_START"
//                intent.addCategory("com.example.activity.MY_CATEGORY")*/
//                startActivityForResult(intent,1)

                ThirdActivity.actionStart(this,"data1","data2")
            }
            R.id.remove -> /*Toast.makeText(this, "click remove", Toast.LENGTH_SHORT).show()*/
            {
                AlertDialog.Builder(this).apply {
                    setTitle("this is dialog")
                    setMessage("hahha")
                    setCancelable(false)
                    setPositiveButton("OK"){
                        dialog, which ->
                    }
                    setNegativeButton("cancel"){
                        dialog, which ->
                    }
                    show()
                }
            }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1-> if(requestCode == Activity.RESULT_OK){
                val returndata = data?.getStringExtra("data_return")
                println(returndata)
            }
        }
    }
}