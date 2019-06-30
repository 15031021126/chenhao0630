package com.bawei.demo02.net;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.util.Log;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

/*
 *@Auther:陈浩
 *@Date: 2019/6/9
 *@Time:19:35
 *@Description:${DESCRIPTION}
 * */
public class HttpUntil {

    private static HttpUntil until;
    private Retrofit retrofit;

    private HttpUntil() {
        OkHttpClient client = new OkHttpClient();
        OkHttpClient build = client.newBuilder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        //第一步构建*/
        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.17.8.100/")
                .client(build)
                .addConverterFactory(GsonConverterFactory.create())
                //添加适配器工厂
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static synchronized HttpUntil getUntil() {
        if (until == null) {
            synchronized (HttpUntil.class) {
                if (until == null) {
                    until = new HttpUntil();
                }
            }
        }
        return until;
    }

    @SuppressLint("CheckResult")
    public void postDataWithParame(String baseurl, String url, String phone, String pwd, final CallBackStr callBackStr) {
        //创建动态代理
        UserApiService userApiService = retrofit.create(UserApiService.class);
        //调用rxjava
        userApiService.login(url, phone, pwd)
                .subscribeOn(Schedulers.io())//Io加载数据
                .observeOn(AndroidSchedulers.mainThread())//显示在主线程
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        callBackStr.success(responseBody.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("123", "accept: " + throwable);
                    }
                });
    }

    public void dogetShop(String baseUrl, String url, String sessionId, String userId, final CallBackStr callBackStr) {

        UserApiService userApiService = retrofit.create(UserApiService.class);
        userApiService.getShop(url, sessionId, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        callBackStr.success(string);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("123", "accept: " + throwable);
                    }
                });
    }


    public <T> T getService(Class<T> tClass) {

        return retrofit.create(tClass);
    }
}
