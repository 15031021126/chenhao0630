package com.bawei.demo02.net;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*
 *@Auther:陈浩
 *@Date: 2019/6/10
 *@Time:14:33
 *@Description:${DESCRIPTION}
 * */public class TestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Log.e("123", "intercept: (\"-->"+ request.method()+"   "+ request.url()+"");

        return chain.proceed(request);//得到响应对象
    }
}
