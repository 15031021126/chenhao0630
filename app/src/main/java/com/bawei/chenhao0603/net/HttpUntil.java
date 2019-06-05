package com.bawei.chenhao0603.net;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bawei.chenhao0603.MyAppliaction;

/*
 *@Auther:陈浩
 *@Date: 2019/6/3
 *@Time:13:54
 *@Description:${网络请求数据}
 * */public class HttpUntil {

    private static HttpUntil until;

    private HttpUntil() {
    }

    public static synchronized HttpUntil getUntil() {

        if (until == null) {
            until = new HttpUntil();
        }
        return until;
    }

    //Vollyget请求
    public void doGetStr(String url, final CallBackStr callBackStr) {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBackStr.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.setTag("get");
        MyAppliaction.getQueue().add(request);
    }
}
