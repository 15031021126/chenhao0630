package com.bawei.chenhao0603.mvp;

import com.bawei.chenhao0603.net.CallBackStr;

/*
 *@Auther:陈浩
 *@Date: 2019/6/3
 *@Time:13:58
 *@Description:${DESCRIPTION}
 * */public interface Ionecontract {
    interface IoneView {
        void showData(String s);
    }

    interface IoneModel {
        void doGetData(String url, CallBackStr callBackStr);
    }

    interface IonePersenter {
        void requestData(String result);
        void atttach(IoneView view);
        void detach();
    }
}
