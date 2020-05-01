package com.example.app.listviewdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R

class MyRecycleViewAdapter(val context :Context, val list:MutableList<Fruit>) : RecyclerView.Adapter<MyRecycleViewAdapter.MyVH>(){

    private lateinit var listener :(item:Fruit)->Unit
    inner class MyVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.fruit_image)
        val textView = itemView.findViewById<TextView>(R.id.fruit_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item,parent,false)
        return MyVH(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        val item = list[position]
         holder.imageView.setImageResource(R.drawable.ic_avatar)
         holder.textView.text = item.fruitName
        holder.itemView.setOnClickListener{
            listener(item)
        }
        holder.imageView.setOnClickListener{
            listener.invoke(item)
        }

    }

    fun setItemClickListener(l: (item:Fruit)->Unit){
        listener = l
    }



}