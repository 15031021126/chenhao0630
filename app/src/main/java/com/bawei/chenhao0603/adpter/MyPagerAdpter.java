package com.bawei.chenhao0603.adpter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/*
 *@Auther:陈浩
 *@Date: 2019/6/3
 *@Time:13:48
 *@Description:${滑动适配器}
 * */public class MyPagerAdpter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private ArrayList<String> strings;
    private Context context;

    public MyPagerAdpter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> strings, Context context) {
        super(fm);
        this.fragments = fragments;
        this.strings = strings;
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
