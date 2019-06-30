package com.bawei.demo02.mvp;

import com.bawei.demo02.mvp.IContract;
import com.bawei.demo02.mvp.ModelImpl;
import com.bawei.demo02.net.CallBackStr;
import com.bawei.demo02.net.HttpUntil;

/*
 *@Auther:陈浩
 *@Date: 2019/6/9
 *@Time:20:20
 *@Description:${DESCRIPTION}
 * */public class PersenterImpl implements IContract.IPersenter {

    private ModelImpl model;
    private IContract.IView view;

    @Override
    public void attach(IContract.IView view) {
        model = new ModelImpl();
        this.view = view;
    }

    @Override
    public void dettach() {
        if (view != null) {
            view = null;
        }
        if (model != null) {
            model = null;
        }
    }

    @Override
    public void requestHome(String baseurl, String url) {
        HttpUntil    until = HttpUntil.getUntil();
        until.dogetShop(baseurl, url, null, null, new CallBackStr() {
            @Override
            public void success(String s) {
                view.showHome(s);
            }
        });
    }

    @Override
    public void requestData(String baseurl, String url,String phone, String pwd) {
         HttpUntil  until = HttpUntil.getUntil();
        until.postDataWithParame( baseurl,url,phone, pwd, new CallBackStr() {
            @Override
            public void success(String s) {
                view.shwoData(s);
            }
        });

    }

    @Override
    public void requestShop(String baseurl, String url ,String sessionId, String userId) {
        HttpUntil    until2 = HttpUntil.getUntil();
        until2 = HttpUntil.getUntil();
            until2.dogetShop(baseurl, url,sessionId,userId, new CallBackStr() {
                @Override
                public void success(String s) {
                    view.shwoShop(s);
                }
            });
    }
}
