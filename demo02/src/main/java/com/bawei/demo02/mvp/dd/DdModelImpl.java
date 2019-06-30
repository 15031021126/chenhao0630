package com.bawei.demo02.mvp.dd;

import android.util.Log;

import com.bawei.demo02.bean.Pedido;
import com.bawei.demo02.net.CallBackObject;
import com.bawei.demo02.net.CallBackStr;
import com.bawei.demo02.net.HttpUntil;
import com.bawei.demo02.net.UserApiService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/*
 *@Auther:陈浩
 *@Date: 2019/6/21
 *@Time:20:50
 *@Description:${DESCRIPTION}
 * */public class DdModelImpl implements DdIcontract.IDdMoldel {
    @Override
    public void getDd(String baseurl, String url, String sessionId, String userId, String status, String page, String count, final CallBackObject callBackStr) {
        UserApiService service = HttpUntil.getUntil().getService(UserApiService.class);
        service.getDD(url, sessionId, userId, status, page, count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Pedido>() {
                    @Override
                    public void accept(Pedido pedido) throws Exception {
                        callBackStr.success(pedido.getOrderList());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("123", "accept: " + throwable.toString());
                    }
                });
    }

    @Override
    public void getDdDelect(String baseurl, String url, String sessionId, String userId, String orderId, final CallBackObject callBackObject) {
        UserApiService service = HttpUntil.getUntil().getService(UserApiService.class);
        service.deleteOrder( sessionId, userId, orderId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        callBackObject.success(responseBody.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("123", "accept: " + throwable);
                    }
                });
    }
}
