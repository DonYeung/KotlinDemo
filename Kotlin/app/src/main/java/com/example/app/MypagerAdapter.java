package com.example.app;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.example.app.listviewdemo.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Don on 2020/7/4
 * DESC:
 */
public class MypagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> datas = new ArrayList<>();
    private Context mContext;

    //对外提供属性
    public Fragment mCurrentFragment;

    private List<Fragment> mFragments;

    public MypagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragments != null) {
            return mFragments.get(position);
        }
        return null;
    }

//    @Override
//    public long getItemId(int position) {
//        return beans.get(position).hashCode();
//    }

    @Override
    public int getCount() {
        return mFragments != null ? mFragments.size() : 0;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        mCurrentFragment = object instanceof Fragment ? (Fragment) object : null;
    }

    /*@Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return beans.get(position).getName();
    }*/

    /**
     * fix bug
     * https://code.google.com/p/android/issues/detail?id=218912
     *
     * @param container
     */
    @Override
    public void finishUpdate(ViewGroup container) {
        try {
            super.finishUpdate(container);
        } catch (Exception ignore) {

        }
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    public List<Fragment> getFragments() {
        return mFragments;
    }

    public void setFragments(List<Fragment> fragments) {
        mFragments = fragments;
        notifyDataSetChanged();
    }

    public void destroy() {

        if (mFragments != null) {
            try {
                for (Fragment fragment : mFragments) {
                    fragment.onDestroy();
                }
            } catch (Exception e) {

            }
            mFragments.clear();
            mFragments = null;
        }
    }

}
