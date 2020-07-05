package com.example.app;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.app.listviewdemo.Fruit;
import com.example.app.view.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Don on 2020/7/4
 * DESC:
 */
public class CityDialogFragment extends DialogFragment {
    private ViewPager viewPager;
    private View mRootView ;
    private MypagerAdapter2 mypagerAdapter2;
    private MypagerAdapter mypagerAdapter;
    private LinearLayout llIndicator;
    private int position= 0 ;
    private TextView tv_title;

    public static CityDialogFragment newInstance() {
        Bundle args = new Bundle();
        CityDialogFragment fragment = new CityDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_dialog,container,false);
//        View view = inflater.inflate(R.layout.activity_dialog, null, false);

        mRootView = view;
        initView();
        return mRootView;
    }
    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();

        if (window != null) {
            /*final Window window = getDialog().getWindow();
            window.setBackgroundDrawable(new ColorDrawable(0x00000000));
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.BOTTOM;
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
            wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setWindowAnimations(R.style.AnimBottom);
            window.setAttributes(wlp);*/



            // 一定要设置Background，如果不设置，window属性设置无效
            /*
            window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
            DisplayMetrics dm = new DisplayMetrics();
            if (getActivity() != null) {
                WindowManager windowManager = getActivity().getWindowManager();
                if (windowManager != null) {
                    windowManager.getDefaultDisplay().getMetrics(dm);
                    WindowManager.LayoutParams params = window.getAttributes();
                    params.gravity = Gravity.CENTER;
                    // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    params.height = 500;
                    window.setAttributes(params);
                }
            }*/
//            window.setBackgroundDrawable(new ColorDrawable(0x00000000));
//            window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
            window = getDialog().getWindow();
            windowParams = window.getAttributes();
            windowParams.dimAmount = 0.8f;
            window.setAttributes(windowParams);
            getDialog().setCanceledOnTouchOutside(false);//点击外部不消失
            //点击返回键不消失，需要监听OnKeyListener:
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    return keyCode == KeyEvent.KEYCODE_BACK;
                }
            });
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    private void initView(){
        viewPager = mRootView.findViewById(R.id.viewPager);
        llIndicator = mRootView.findViewById(R.id.ll_indicator);
        tv_title = mRootView.findViewById(R.id.tv_title);
        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });

        List<Fruit> list = new ArrayList<>();
        list.add(new Fruit(R.drawable.ic_avatar, "apple"));
        list.add(new Fruit(R.drawable.ic_avatar, "pear"));
        list.add(new Fruit(R.drawable.ic_avatar, "bear"));
        mypagerAdapter2 = new MypagerAdapter2(getActivity());
        mypagerAdapter2.setData(list);

        viewPager.setPageMargin(30);
        viewPager.setPageTransformer(false, new MyPageTransformer());

        viewPager.setAdapter(mypagerAdapter2);
        mypagerAdapter2.notifyDataSetChanged();


       /*List<Fragment> list = new ArrayList<>();
       list.add(ListViewFragment.newInstance("1"));
       list.add(ListViewFragment.newInstance("1"));
       list.add(ListViewFragment.newInstance("1"));
        mypagerAdapter = new MypagerAdapter(getChildFragmentManager());
        mypagerAdapter.setFragments(list);
        viewPager.setAdapter(mypagerAdapter);*/

        //默认选第一个
        if (list != null && list.size() > 0) {
            viewPager.setCurrentItem(0, false);
        }
        for (int i = 0; i < list.size(); i++) {
            View indicator = new View(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(UIUtil.dp2px( 16), UIUtil.dp2px(  4));
            layoutParams.setMargins(8, 0, 8, 0);
            indicator.setLayoutParams(layoutParams);
            if (0 == i) {
                indicator.setBackground(getResources().getDrawable(R.drawable.viewpager_indicator_sele));
            } else {
                indicator.setBackground(getResources().getDrawable(R.drawable.viewpager_indicator));
            }
            llIndicator.addView(indicator);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (llIndicator.getChildCount() >= i) {
                    for (int j = 0; j < llIndicator.getChildCount(); j++) {
                        if (j == i) {
                            llIndicator.getChildAt(j).setBackground(getResources().getDrawable(R.drawable.viewpager_indicator_sele));
                        } else {
                            llIndicator.getChildAt(j).setBackground(getResources().getDrawable(R.drawable.viewpager_indicator));
                        }
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void dismiss() {
        dismissAllowingStateLoss();
    }
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}
