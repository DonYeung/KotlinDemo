package com.example.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.entity.Fruit
import kotlinx.android.synthetic.main.activity_test.view.*
import kotlinx.android.synthetic.main.fruit_item.view.*

class FruitRecycleAdapter(val fruits: List<Fruit>) : RecyclerView.Adapter<FruitRecycleAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage = view.findViewById<ImageView>(R.id.fruit_image)
        val fruitName = view.findViewById<TextView>(R.id.fruit_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.fruit_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fruits.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruits[position]
        holder.fruitImage.setImageResource(R.drawable.iv_avatar)
        holder.fruitName.text = fruit.name

    }
}