package com.example.app.contentProviderdemo

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import kotlinx.android.synthetic.main.activity_listview.*

class ContentProviderDemo :AppCompatActivity() {
    private var list  = mutableListOf<String>()
    private lateinit var adapter :RecycleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)


        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
//        val
         adapter = RecycleAdapter(list)
        recycleview.layoutManager = manager
        recycleview.adapter = adapter

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),1)

        }else{
            readContacts()

        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1->{
                if (grantResults.isNotEmpty() &&
                        grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    readContacts()
                }else{
                    Toast.makeText(this,"拒绝权限", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    fun readContacts(){
        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null,null)?.apply{
            while (moveToNext()){
                val displayName = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number = getString(getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                ))
                list.add("$displayName,$number")
                Log.i("Don", "readContacts: $displayName,$number")
            }
            adapter.setData(list)
            adapter.notifyDataSetChanged()
            close()

        }

    }
    inner class RecycleAdapter(var datatList :MutableList<String>) : RecyclerView.Adapter<RecycleAdapter.MyVH>() {
        inner class MyVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textView = itemView.findViewById<TextView>(R.id.fruit_text)
            val imageView = itemView.findViewById<ImageView>(R.id.fruit_image)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item,parent,false)
            return MyVH(view)
        }

        fun setData(list:MutableList<String>){
            datatList = list
            notifyDataSetChanged()
        }
        override fun getItemCount(): Int {
            return datatList.size
        }

        override fun onBindViewHolder(holder: MyVH, position: Int) {
            val phoneNum = datatList[position]
            holder.textView.text = phoneNum
            holder.imageView.setImageResource(R.drawable.iv_avatar)
        }
    }
}
