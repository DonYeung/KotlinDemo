package com.example.app.fanxing;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.app.fanxing.bean.Apple;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppleArrayList<T extends Apple> extends ArrayList {


    ArrayList<? extends View> fruitList = new ArrayList<TextView>();

    ArrayList<? super AppCompatTextView> fruitList2 = new ArrayList<TextView>();

   public  <O extends Runnable & Serializable> void someMethod(O param){

    }



    public void merge(Object item, List<Object> list){
        list.add(item);
    }
    //改写后
    public <P>  void merge2(P item,List<P> list){
        list.add(item);
    }

}
