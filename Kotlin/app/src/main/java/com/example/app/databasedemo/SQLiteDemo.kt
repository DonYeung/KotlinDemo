package com.example.app.databasedemo

import android.content.ContentValues
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import com.example.app.cvOf
import kotlinx.android.synthetic.main.activity_test.*

class SQLiteDemo :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
//        val dpHelper = MySQLiteHelper(this,"BookStore.db",1)
        val dbHelper = MySQLiteHelper(this,"BookStore.db",2)
        text.setOnClickListener {
            dbHelper.writableDatabase

        }
        //插入
        val db= dbHelper.writableDatabase
        val contentValue = ContentValues().apply {
            put("name","hahah")
            put("author","dandan")
            put("pages",54)
            put("price",16.96)
        }
        db.insert("Book",null,contentValue)
        val contentValues2 = ContentValues().apply {
            put("name","he1111")
            put("author","qqqq")
            put("pages",192)
            put("price",62.96)
        }
        db.insert("Book",null,contentValues2)

        //更新
        val contentValues3 = ContentValues().apply {
            put("price",100.21)
        }
        db.update("Book",contentValues3,"name =？", arrayOf("hahah"))


        //删除数据
        db.delete("Book","page > ?", arrayOf("100"))

        //查询数据
        val cursor = db.query("Book",null,null,null,null,null,null)
        if(cursor.moveToFirst()){
            do{
                //遍历cursor对象，取出数据并打印
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val author = cursor.getString(cursor.getColumnIndex("author"))
                val pages = cursor.getString(cursor.getColumnIndex("pages"))
                val price = cursor.getString(cursor.getColumnIndex("price"))

                println("name,$name,author$author,pages$pages,price,$price")
            }while(cursor.moveToNext())
            cursor.close()
        }


        //使用新特性
        val value = cvOf("name" to "haha", "author" to "don", "pages" to 720,"price" to 20.23)
        db.insert("Book",null,value)
    }
}