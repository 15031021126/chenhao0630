package com.bawei.demo02.mvp;

import com.bawei.demo02.net.CallBackStr;
import com.bawei.demo02.net.HttpUntil;

/*
 *@Auther:陈浩
 *@Date: 2019/6/9
 *@Time:20:01
 *@Description:${DESCRIPTION}
 * */public class ModelImpl implements IContract.IMoldel {


    @Override
    public void getHome(String baseurl, String url, CallBackStr callBackStr) {
        HttpUntil until = HttpUntil.getUntil();
        until.dogetShop(baseurl,url,null,null,callBackStr);
    }

    @Override
    public void doGetStr(String baseurl, String url, String phone, String pwd, CallBackStr callBackStr) {
        HttpUntil until = HttpUntil.getUntil();
        until.postDataWithParame(phone, pwd, baseurl, url, callBackStr);
    }

    @Override
    public void doGetShop(String baseurl, String url, String sessionId, String userId, CallBackStr callBackStr) {
        HttpUntil until = HttpUntil.getUntil();
        until.dogetShop(baseurl, url, sessionId, userId, callBackStr);
    }
}
