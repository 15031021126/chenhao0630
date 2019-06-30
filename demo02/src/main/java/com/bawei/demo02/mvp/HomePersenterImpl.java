package com.bawei.demo02.mvp;

import android.util.Log;

import com.bawei.demo02.net.CallBackStr;
import com.bawei.demo02.net.HttpUntil;
import com.bawei.demo02.net.UserApiService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/*
 *@Auther:陈浩
 *@Date: 2019/6/20
 *@Time:19:42
 *@Description:${DESCRIPTION}
 * */public class HomePersenterImpl implements HomeIcontract.IHomePersenter {

    private HomeModelImpl model;
    private HomeIcontract.IHomeView view;

    @Override
    public void attache(HomeIcontract.IHomeView view) {
        model = new HomeModelImpl();
        this.view = view;
    }

    @Override
    public void dettache() {
        if (model != null) {
            model = null;
        }
        if (view != null) {
            view = null;
        }
    }

    @Override
    public void requestHome(String baseurl, String url) {
        model.getHome(baseurl, url, new CallBackStr() {
            @Override
            public void success(String s) {
                Log.e("123", "success: "+s );
                view.showHome(s);
            }
        });

    }
}
