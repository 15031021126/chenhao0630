package com.bawei.lxewm;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/*
 *@Auther:陈浩
 *@Date: 2019/6/6
 *@Time:14:51
 *@Description:${DESCRIPTION}
 * */public class MyAppliaction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
    }
}
