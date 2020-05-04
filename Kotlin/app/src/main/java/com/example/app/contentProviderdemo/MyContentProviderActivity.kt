package com.example.app.contentProviderdemo

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import com.example.app.cvOf

class MyContentProviderActivity :AppCompatActivity(){
    var bookId :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

    }
    fun insert(){
        val uri = Uri.parse("content://com.example.hencoder.provider/book")
        val value = cvOf("name" to "king","author" to "lolin","pages" to 1040,
                "price" to 22.85)
        val newUri = contentResolver.insert(uri,value)
        bookId = newUri?.pathSegments?.get(1)

    }
    fun query(){
        val uri = Uri.parse("content://com.example.hencoder.provider/book")
        contentResolver.query(uri,null,null,null,null)?.apply {
            while (moveToNext()){
                val name = getString(getColumnIndex("name"))
                val author = getString(getColumnIndex("author"))
                val pages = getInt(getColumnIndex("pages"))
                val price = getDouble(getColumnIndex("price"))
            }
        }

    }

    fun update(){
        val uri = Uri.parse("content://com.example.hencoder.provicer/book/$bookId")
        val values = cvOf("name" to "Don","pages" to 1216, "price" to 24.05)
        contentResolver.update(uri,values,null,null)
    }

    fun delete(){
        val uri = Uri.parse("content://com.example.hencoder.provider/book$bookId")
        contentResolver.delete(uri,null,null)
    }
}