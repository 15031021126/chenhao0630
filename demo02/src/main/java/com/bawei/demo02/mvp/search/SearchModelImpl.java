package com.bawei.demo02.mvp.search;

import android.util.Log;

import com.bawei.demo02.mvp.HomeIcontract;
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
 *@Time:19:39
 *@Description:${DESCRIPTION}
 * */public class SearchModelImpl implements ISearchContract.ISearchMoldel {

    @Override
    public void getSearch(String url, final CallBackStr callBackStr) {
        HttpUntil until = HttpUntil.getUntil();
        UserApiService service = until.getService(UserApiService.class);
        service.getShop(url, null, null).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                })
        ;
    }
}
