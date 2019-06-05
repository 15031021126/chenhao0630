package com.bawei.chenhao0603.mvp;

import com.bawei.chenhao0603.net.CallBackStr;
import com.bawei.chenhao0603.net.HttpUntil;

/*
 *@Auther:陈浩
 *@Date: 2019/6/3
 *@Time:14:00
 *@Description:${P层}
 * */public class OnePersenterImpl implements Ionecontract.IonePersenter {
    private Ionecontract.IoneView view;
    private OneMoldeImpl molde;

    @Override
    public void requestData(String result) {
        HttpUntil until = HttpUntil.getUntil();
        until.doGetStr(result, new CallBackStr() {
            @Override
            public void success(String s) {
                view.showData(s);
            }
        });
    }

    @Override
    public void atttach(Ionecontract.IoneView view) {
        molde = new OneMoldeImpl();
        this.view = view;
    }

    @Override
    public void detach() {
        if (view != null) {
            view = null;
        }
        if (molde != null) {
            molde = null;
        }
        System.gc();//内存处理
    }
}
