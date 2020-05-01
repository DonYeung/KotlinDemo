package com.example.app.fragmentdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app.R

class MyFragment2 : Fragment() {

    private lateinit var myview: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myview = LayoutInflater.from(context).inflate(R.layout.activity_constaint2,container,false)
        return myview
    }

}