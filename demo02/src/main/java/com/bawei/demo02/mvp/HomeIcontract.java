package com.bawei.demo02.mvp;

import com.bawei.demo02.net.CallBackStr;

/*
 *@Auther:陈浩
 *@Date: 2019/6/20
 *@Time:19:33
 *@Description:${DESCRIPTION}
 * */public interface HomeIcontract {
     interface IHomeView {
        void showHome(String str);
    }

     interface IHomeMoldel {
        void getHome(String baseurl, String url, CallBackStr callBackStr);
    }

      interface IHomePersenter {
        void attache(IHomeView view);

        void dettache();

        void requestHome(String baseurl, String url);
    }
}
