package com.bawei.chenhao0603;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/*
 *@Auther:陈浩
 *@Date: 2019/6/3
 *@Time:13:52
 *@Description:${volly}
 * */public class MyAppliaction extends Application {
    public static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getQueue() {
        return queue;
    }
}
