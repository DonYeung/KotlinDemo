package com.example.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * Create by Don on 2020/7/5
 * DESC:
 */
public class ListViewFragment extends Fragment {

    public static final ListViewFragment newInstance(String sid){
        ListViewFragment fragment = new ListViewFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_dianxin, container, false);


        return v;
    }
}
