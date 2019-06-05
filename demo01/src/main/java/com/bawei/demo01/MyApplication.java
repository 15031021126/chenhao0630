package com.bawei.demo01;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/*
 *@Auther:陈浩
 *@Date: 2019/6/5
 *@Time:20:02
 *@Description:${DESCRIPTION}
 * */public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
    }
}
