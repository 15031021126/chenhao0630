package com.bawei.chenhao0603.mvp;

import com.bawei.chenhao0603.net.CallBackStr;
import com.bawei.chenhao0603.net.HttpUntil;

/*
 *@Auther:陈浩
 *@Date: 2019/6/3
 *@Time:13:59
 *@Description:${DESCRIPTION}
 * */public class OneMoldeImpl implements Ionecontract.IoneModel {
    @Override
    public void doGetData(String url, CallBackStr callBackStr) {
        HttpUntil until = HttpUntil.getUntil();
        until.doGetStr(url,callBackStr);
    }
}
