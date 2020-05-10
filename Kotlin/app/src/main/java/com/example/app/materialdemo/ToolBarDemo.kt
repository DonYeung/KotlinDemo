package com.example.app.materialdemo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import kotlinx.android.synthetic.main.activity_materiral_layout.*

class ToolBarDemo:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materiral_layout)
        setSupportActionBar(toolbar)

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
        }
        return true
    }
}