package com.bawei.demo02.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.demo02.R;
import com.bawei.demo02.adpter.MyFragAdpter;
import com.bawei.demo02.view.fragment.Frag01;
import com.bawei.demo02.view.fragment.Frag02;
import com.bawei.demo02.view.fragment.Frag03;
import com.bawei.demo02.view.fragment.Frag04;
import com.bawei.demo02.view.fragment.Frag05;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

//import butterknife.BindView;

public class ThreeActivity extends AppCompatActivity {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.pager)
    ViewPager pager;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> strings;
    private ArrayList<Integer> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        //tab = findViewById(R.id.tab);
        // pager = findViewById(R.id.pager);
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        fragments.add(new Frag01());
        fragments.add(new Frag02());
        fragments.add(new Frag03());
        fragments.add(new Frag04());
        fragments.add(new Frag05());
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());
        strings = new ArrayList<>();
        strings.add("首页");
        strings.add("圈子");
        strings.add("购物车");
        strings.add("订单");
        strings.add("我的");
        arrayList = new ArrayList<>();
        arrayList.add(R.drawable.sel);
        arrayList.add(R.drawable.sel2);
        arrayList.add(R.drawable.sel3);
        arrayList.add(R.drawable.sel4);
        arrayList.add(R.drawable.sel5);

        tab.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                tab.setScrollPosition(pager.getCurrentItem(), 0, false);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        /**
         * 添加tab选中状态
         */
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //适配器
        pager.setAdapter(new MyFragAdpter(getSupportFragmentManager(), fragments, strings, this));
        tab.getTabAt(0).setCustomView(setTabImage(0));
        tab.getTabAt(1).setCustomView(setTabImage(1));
        tab.getTabAt(2).setCustomView(setTabImage(2));
        tab.getTabAt(3).setCustomView(setTabImage(3));
        tab.getTabAt(4).setCustomView(setTabImage(4));
    }

    /**
     * 设置图片
     *
     * @param i
     * @return
     */
    public View setTabImage(int i) {
        View view = LayoutInflater.from(this).inflate(R.layout.item, null);
        ImageView imv = view.findViewById(R.id.tabimg);
        TextView tabtv = view.findViewById(R.id.tabtv);
        imv.setImageResource(R.drawable.sel);
        imv.setImageResource(arrayList.get(i));
        tabtv.setText(strings.get(i));
        return view;
    }

}
