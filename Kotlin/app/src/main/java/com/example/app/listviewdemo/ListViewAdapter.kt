package com.example.app.listviewdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.app.R

class ListViewAdapter(context: Context, val resource: Int,  objects: MutableList<Fruit>) : ArrayAdapter<Fruit>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view :View
        val viewHolder: ViewHolder
        if (convertView==null){
            view = LayoutInflater.from(context).inflate(resource,parent,false)
            val fruit_image = view.findViewById<ImageView>(R.id.fruit_image)
            val fruit_text = view.findViewById<TextView>(R.id.fruit_text)
            viewHolder = ViewHolder(fruit_image,fruit_text)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val item = getItem(position)
        viewHolder.fruitImageView.setImageResource(R.drawable.ic_avatar)
        viewHolder.fruitNameTV.text = item?.fruitName

        return view
    }
    inner class ViewHolder(val fruitImageView:ImageView,val fruitNameTV :TextView)


}