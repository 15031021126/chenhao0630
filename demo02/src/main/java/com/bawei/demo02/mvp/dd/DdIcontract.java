package com.bawei.demo02.mvp.dd;

import com.bawei.demo02.net.CallBackObject;
import com.bawei.demo02.net.CallBackStr;

/*
 *@Auther:陈浩
 *@Date: 2019/6/20
 *@Time:19:33
 *@Description:${DESCRIPTION}
 * */public interface DdIcontract {
    interface IHomeView {
        void showDd(Object str);

        void showDdDelect(Object str);
    }

    interface IDdMoldel {
        void getDd(String baseurl, String url, String sessionId, String userId, String status, String page, String count, CallBackObject callBackObject);

        void getDdDelect(String baseurl, String url, String sessionId, String userId, String orderId,CallBackObject callBackObject);
    }

    interface IDdPersenter {
        void attache(IHomeView view);

        void dettache();

        void requestDd(String baseurl, String url, String sessionId, String userId, String status, String page, String count);

        void requestDelect(String baseurl, String url, String sessionId, String userId, String orderId);
    }
}
