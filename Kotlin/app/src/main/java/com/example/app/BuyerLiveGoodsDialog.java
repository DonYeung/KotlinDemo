package com.example.app;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.example.app.fragmentdemo.MyFragment;
import com.example.app.listviewdemo.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Don on 2020/7/5
 * DESC:
 */
public class BuyerLiveGoodsDialog  extends DialogFragment {

    private ViewPager viewPager;
    private int height;

    public static BuyerLiveGoodsDialog newInstance() {

        Bundle args = new Bundle();

        BuyerLiveGoodsDialog fragment = new BuyerLiveGoodsDialog();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        height =500;
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.activity_dialog, container, false);
        viewPager = view.findViewById(R.id.viewPager);


        BuyerLiveGoodsPageAdapter pageAdapter = new BuyerLiveGoodsPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(pageAdapter);
//        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        if (window != null) {
            // 一定要设置Background，如果不设置，window属性设置无效
            window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
            DisplayMetrics dm = new DisplayMetrics();
            if (getActivity() != null) {
                WindowManager windowManager = getActivity().getWindowManager();
                if (windowManager != null) {
                    windowManager.getDefaultDisplay().getMetrics(dm);
                    WindowManager.LayoutParams params = window.getAttributes();
                    params.gravity = Gravity.BOTTOM;
                    // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    params.height = height;
                    window.setAttributes(params);
                }
            }
        }
    }

    static class BuyerLiveGoodsPageAdapter extends FragmentPagerAdapter {

        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();

        public BuyerLiveGoodsPageAdapter(FragmentManager fm) {
            super(fm);

            // 一口价
            ListViewFragment myFragment = ListViewFragment.newInstance("22");
            fragments.add(myFragment);
            titles.add("一口价");
            // 拍卖
            ListViewFragment myFragment2 = ListViewFragment.newInstance("33");
            fragments.add(myFragment2);
            titles.add("拍卖");

            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    public static void showDialog(FragmentManager fragmentManager) {
        BuyerLiveGoodsDialog dialog = new BuyerLiveGoodsDialog();
        dialog.show(fragmentManager, "tag");
    }


}
