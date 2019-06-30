package com.bawei.demo02.mvp.dd;

import com.bawei.demo02.net.CallBackObject;
import com.bawei.demo02.net.CallBackStr;

/*
 *@Auther:陈浩
 *@Date: 2019/6/21
 *@Time:20:52
 *@Description:${DESCRIPTION}
 * */public class DdPsersenterImpl implements DdIcontract.IDdPersenter {
    private DdIcontract.IHomeView view;
    private DdModelImpl model;

    @Override
    public void attache(DdIcontract.IHomeView view) {
        this.view = view;
        model = new DdModelImpl();
    }

    @Override
    public void dettache() {
        if (view != null) {
            view = null;
        }
        if (model != null) {
            model = null;
        }
    }

    @Override
    public void requestDd(String baseurl, String url, String sessionId, String userId, String status, String page, String count) {
                model.getDd(baseurl, url, sessionId, userId, status, page, count, new CallBackObject() {
                            @Override
                            public void success(Object obj) {
                                view.showDd(obj);
                            }
                        }
                );
    }

    @Override
    public void requestDelect(String baseurl, String url, String sessionId, String userId, String orderId) {
        model.getDdDelect(baseurl, url, sessionId, userId, orderId, new CallBackObject() {
            @Override
            public void success(Object obj) {
                view.showDdDelect(obj);
            }
        });
    }
}
