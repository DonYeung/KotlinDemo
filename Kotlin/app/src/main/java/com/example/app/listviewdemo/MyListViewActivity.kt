package com.example.app.listviewdemo

import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Layer
import androidx.constraintlayout.solver.state.State
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Placeholder
import androidx.fragment.app.FragmentManager
import com.example.app.R
import com.example.app.fragmentdemo.MyFragment
import com.example.app.fragmentdemo.MyFragment2
import com.example.app.testkotlin.onclick
import kotlinx.android.synthetic.main.activity_constaint2.*
import kotlinx.android.synthetic.main.activity_dianxin.*
import kotlinx.android.synthetic.main.activity_fragment.*
import kotlinx.android.synthetic.main.activity_placeholder.*
import kotlin.contracts.contract

class MyListViewActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        /*val fragment = MyFragment()
        val manager = supportFragmentManager
        manager.beginTransaction().apply {
            replace(R.id.content,fragment)
//            add(R.id.content,fragment)
            commit()
        }*/
        val button = findViewById<ImageView>(R.id.iv_1)
        button.setOnClickListener {
            val fragment2 = MyFragment2()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.content,fragment2)
//                addToBackStack(null)
                commit()
            }
        }
//        manager.findFragmentById(R.id.content)



        val listdata = mutableListOf<Fruit>(
                Fruit(R.drawable.ic_avatar, "apple"),
                Fruit(R.drawable.ic_avatar, "pear"),
                Fruit(R.drawable.ic_avatar, "bear"),
                Fruit(R.drawable.ic_avatar, "cat"),
                Fruit(R.drawable.ic_avatar, "sada"),
                Fruit(R.drawable.ic_avatar, "safd"),
                Fruit(R.drawable.ic_avatar, "apple"),
                Fruit(R.drawable.ic_avatar, "gdgd"),
                Fruit(R.drawable.ic_avatar, "tbxcb"),
                Fruit(R.drawable.ic_avatar, "appdwle"),
                Fruit(R.drawable.ic_avatar, "zxvn"),
                Fruit(R.drawable.ic_avatar, "apvxcple"),
                Fruit(R.drawable.ic_avatar, "zvn"),
                Fruit(R.drawable.ic_avatar, "sada"),
                Fruit(R.drawable.ic_avatar, "safd"),
                Fruit(R.drawable.ic_avatar, "apple"),
                Fruit(R.drawable.ic_avatar, "gdgd"),
                Fruit(R.drawable.ic_avatar, "tbxcb"),
                Fruit(R.drawable.ic_avatar, "appdwle"),
                Fruit(R.drawable.ic_avatar, "zxvn"),
                Fruit(R.drawable.ic_avatar, "apvxcple"),
                Fruit(R.drawable.ic_avatar, "zvn"),
                Fruit(R.drawable.ic_avatar, "sada"),
                Fruit(R.drawable.ic_avatar, "safd"),
                Fruit(R.drawable.ic_avatar, "apple"),
                Fruit(R.drawable.ic_avatar, "gdgd"),
                Fruit(R.drawable.ic_avatar, "tbxcb"),
                Fruit(R.drawable.ic_avatar, "appdwle"),
                Fruit(R.drawable.ic_avatar, "zxvn"),
                Fruit(R.drawable.ic_avatar, "apvxcple"),
                Fruit(R.drawable.ic_avatar, "zvn"),
                Fruit(R.drawable.ic_avatar, "nhf")


        )

        //layer
        /*val btn = findViewById<Button>(R.id.btn)
        val layer = findViewById<Layer>(R.id.layer)
        btn.setOnClickListener {

            layer.apply {
                translationX = 100f
                rotation =45f
                translationY = 200f
            }
        }*/

        //group
        /*val btn = findViewById<Button>(R.id.btn)
        val group = findViewById<Group>(R.id.group)
        btn.setOnClickListener {
            group.visibility = View.GONE
        }*/




        /*val adapter = ListViewAdapter(this, R.layout.fruit_item, listdata)
        list_view.adapter = adapter
        list_view.setOnItemClickListener{
            parent, view, position, id ->
             Toast.makeText(this,listdata[position].fruitName,Toast.LENGTH_SHORT).show()
        }*/

/*        val lineManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)

//        lineManager.orientation = LinearLayoutManager.VERTICAL
        lineManager.scrollToPosition(listdata.size-1)
        val adapter = MyRecycleViewAdapter(this, listdata)
        recycle_list.layoutManager = lineManager
        recycle_list.adapter = adapter
        adapter.setItemClickListener {
             Toast.makeText(this,"ppp${it.fruitName}",Toast.LENGTH_SHORT).show()
        }*/



        //constraintSet
       /* btn1.setOnClickListener {
            val constraintSet = ConstraintSet().apply {
                isForceId = false
                TransitionManager.beginDelayedTransition(view)
                clone(this@MyListViewActivity,R.layout.activity_constaint1)
            }
            constraintSet.applyTo(view)
        }*/

    }
    /*fun OnClick(view:View){
        findViewById<Placeholder>(R.id.placeholder).setContentId(view.id)
    }*/

}