package com.example.app.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.app.R
import com.example.app.entity.Fruit
import kotlinx.android.synthetic.main.fruit_item.view.*

class FruitAdapter(val activity: Activity, val resourceId: Int, data: MutableList<Fruit>)
    : ArrayAdapter<Fruit>(activity, resourceId, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder :ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId,parent,false)
            val fruitImage = view.findViewById<ImageView>(R.id.fruiv)
            val fruname = view.findViewById<TextView>(R.id.fruname)
            viewHolder=ViewHolder(fruitImage,fruname)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val fruit = getItem(position) //获取当期项的Fruit实列

        if (fruit != null) {
            viewHolder.fruitImage.setImageResource(R.drawable.iv_avatar)
            viewHolder.fruitName.text = fruit.name
        }

        return view


    }
    inner class ViewHolder(val fruitImage:ImageView,val fruitName:TextView)
}