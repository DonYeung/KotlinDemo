package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.app.listviewdemo.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Don on 2020/7/5
 * DESC:
 */
public class MypagerAdapter2 extends PagerAdapter {
    private List<Fruit> datas = new ArrayList<>();
    private Context mContext;
    public MypagerAdapter2(Context context){
        mContext = context;
    }

    public void setData(List<Fruit> list){
        datas=list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_page,container,false);
        TextView textView = view.findViewById(R.id.textitem);
        textView.setText(datas.get(position).getFruitName());
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
