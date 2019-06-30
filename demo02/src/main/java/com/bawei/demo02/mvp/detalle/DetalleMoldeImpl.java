package com.bawei.demo02.mvp.detalle;

import android.util.Log;

import com.bawei.demo02.bean.DetalleEntity;
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
 *@Date: 2019/6/26
 *@Time:20:17
 *@Description:${DESCRIPTION}
 * */public class DetalleMoldeImpl implements IDetallecontract.IDetallModel {
    @Override
    public void doGetDetall(String userId, String sessionId, String id, final CallBackStr callBackStr) {
        HttpUntil.getUntil().getService(UserApiService.class).getDetalle(userId, sessionId, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        callBackStr.success(responseBody.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("123", "accept: " + throwable.toString());
                    }
                });
    }
}
