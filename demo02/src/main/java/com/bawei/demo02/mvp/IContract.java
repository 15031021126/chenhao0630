package com.bawei.demo02.mvp;

import com.bawei.demo02.net.CallBackStr;

/*
 *@Auther:陈浩
 *@Date: 2019/6/9
 *@Time:19:38
 *@Description:${DESCRIPTION}
 * */public interface IContract {
    interface IView {
        void shwoData(String s);

        void shwoShop(String s);

        void showHome(String str);
    }

    interface IMoldel {
        void getHome(String baseurl, String url, CallBackStr callBackStr);

        void doGetStr(String baseurl, String url, String phone, String pwd, CallBackStr callBackStr);

        void doGetShop(String baseurl, String url, String sessionId, String userId, CallBackStr callBackStr);
    }

    interface IPersenter {
        void attach(IView view);

        void dettach();

        void requestHome(String baseurl, String url);

        void requestData(String baseurl, String url, String phone, String pwd);

        void requestShop(String baseurl, String url, String sessionId, String userId);
    }
}
